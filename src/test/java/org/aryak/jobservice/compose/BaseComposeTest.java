package org.aryak.jobservice.compose;

import org.junit.ClassRule;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
public abstract class BaseComposeTest {

    public static final String MONO_SERVICE = "mongodb";
    public static final int MONGO_PORT = 27017;
    public static final String MONGO_URI_FORMAT = "mongodb://%s:%s/job";

    @SuppressWarnings("all")
    @Container
    public static final DockerComposeContainer<?> compose = new DockerComposeContainer<>(new File("src/test/resources/docker.yaml"))
            .withEnv("MONGO_PORT", "0") // specify random port
            .withExposedService(MONO_SERVICE, MONGO_PORT, Wait.forListeningPort());

    @DynamicPropertySource
    static void mongoProps(DynamicPropertyRegistry registry) {

        // get mapped port
        int port = compose.getServicePort(MONO_SERVICE, MONGO_PORT);
        String host = compose.getServiceHost(MONO_SERVICE, MONGO_PORT);
        var uri = String.format(MONGO_URI_FORMAT, host, port);
        System.out.println("Connection string : " + uri);

        // add to registry
        registry.add("spring.data.mongodb.uri", () -> uri);
    }
}
