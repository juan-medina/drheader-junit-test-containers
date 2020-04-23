## drHEADer JUnit tests with Test Containers

This is an example about how to run Santander Security Research drHEADer analysis tools as a JUnit test
that could run in your development IDE before committing any code.

Because it uses TestContainers you need to have installed and configure your docker client.

For this example we have created a basic Spring WebFlux application with an static resource and Spring Security setup
accordingly.

To run this just do :

```shell script
$ ./mvnw clean test
```
The first time that you run this test it may take longer since it need to pull the base images from the docker registry.

