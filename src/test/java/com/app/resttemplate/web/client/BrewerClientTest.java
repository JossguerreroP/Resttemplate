package com.app.resttemplate.web.client;

import com.app.resttemplate.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class BrewerClientTest {

    @Autowired
    BrewerClient client;
    @Test
    void getBeerById() {
        BeerDto DTO =client.getBeerById(UUID.randomUUID());
        assertNotNull(DTO);
    }
}