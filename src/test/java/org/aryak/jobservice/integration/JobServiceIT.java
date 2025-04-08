package org.aryak.jobservice.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class JobServiceIT extends BaseTest {

    @Autowired
    WebTestClient client;

    @Test
    void testGetAllJobs() {

        this.client.get()
                .uri("/all")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$")
                .isNotEmpty();

    }

}
