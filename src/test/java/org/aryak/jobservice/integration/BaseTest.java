package org.aryak.jobservice.integration;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public abstract class BaseTest {

    public static final String INIT_JS_LOCAL = "./data/init.js";
    public static final String INIT_JS_CONTAINER = "/docker-entrypoint-initdb.d/init.js";
    public static final int MONGO_PORT = 27017;
    public static final String MONGO_URI_FORMAT = "mongodb://%s:%s/job";
    public static final DockerImageName image = DockerImageName.parse("mongo");

    @SuppressWarnings("all")
    @Container
    public static final GenericContainer<?> mongo = new GenericContainer<>(image)
            .withExposedPorts(MONGO_PORT)
            .withClasspathResourceMapping(INIT_JS_LOCAL, INIT_JS_CONTAINER, BindMode.READ_ONLY)
            .waitingFor(Wait.forListeningPort()); // container defined

    @DynamicPropertySource
    static void mongoProps(DynamicPropertyRegistry registry){
        // start the container
        mongo.start();

        // get mapped port
        int port = mongo.getMappedPort(MONGO_PORT);
        String host = mongo.getHost();
        var uri = String.format(MONGO_URI_FORMAT, host, port);
        System.out.println("Connection string : "+ uri);

        // add to registry
        registry.add("spring.data.mongodb.uri", () -> uri);
    }
}
