## drHEADer JUnit tests with Test Containers

This is an example about how to run [Santander Security Research drHEADer](https://github.com/Santandersecurityresearch/DrHeader) analysis tools as a JUnit test
that could run in your development IDE before committing any code.

Because it uses [TestContainers](https://www.testcontainers.org/) you need to have installed and configure your docker client.

For this example we have created a basic [Spring WebFlux](https://spring.io/reactive) application with a static resource and [Spring Security](https://spring.io/projects/spring-security) setup
accordingly.

To run this just do :

```shell script
$ ./mvnw clean test
```
The first time that you run this test it may take longer since it need to pull the base images from the docker registry.

If you like to review the test, you could do it [here](src/test/java/com/medina/juan/drheaderjunittestcontainers/DrTests.java).
