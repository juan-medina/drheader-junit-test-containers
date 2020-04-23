package com.medina.juan.drheaderjunittestcontainers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.testcontainers.Testcontainers;
import org.testcontainers.containers.Container.ExecResult;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DrTests {
    private static final String TEST_CONTAINERS_INTERNAL_URL = "http://host.testcontainers.internal:%d%s";
    private static final String NO_ISSUES = "No issues found!";

    @LocalServerPort
    private Integer port;

    @SuppressWarnings("rawtypes")
    private GenericContainer drHEADerContainer;

    @BeforeEach
    void setup() {
        Testcontainers.exposeHostPorts(port);
        drHEADerContainer = new GenericContainer<>(
            new ImageFromDockerfile()
                .withDockerfileFromBuilder(builder ->
                    builder
                        .from("python:3.7.4")
                        .run("git clone https://github.com/Santandersecurityresearch/DrHeader.git")
                        .run("cd DrHeader && pip install .")
                        .entryPoint("tail -f /dev/null")
                        .build()));
        drHEADerContainer.start();
    }

    @AfterEach
    void tearDown() {
        drHEADerContainer.stop();
    }

    private void drHEADer(final String url) throws Exception {
        final String testUrl = String.format(TEST_CONTAINERS_INTERNAL_URL, port, url);
        final ExecResult execResult = drHEADerContainer.execInContainer("drheader", "scan", "single", testUrl);
        drHEADerContainer.stop();

        final String out = execResult.getStdout().trim();
        if (!out.equals(NO_ISSUES)) {
            throw new AssertionError(String.format("Error on drHEADer analysis : \n%s", out));
        }
    }

    @Test
    @DisplayName("Index should not have drHEADer errors")
    void IndexShouldNotHaveDrHEADerErrors() throws Exception {
        drHEADer("/index.html");
    }
}
