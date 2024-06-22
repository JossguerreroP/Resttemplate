package com.app.resttemplate.web.client;

import com.app.resttemplate.web.model.BeerDto;
import com.app.resttemplate.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;
import java.net.URI;
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

    @Test
    void testSaveNewBeer(){
        BeerDto beerDto = BeerDto.builder().beerStyle("New Beer").build();
        URI uri = client.saveNewBeer(beerDto);
        assertNotNull(uri);
        System.out.println(uri.toString());
    }

    @Test
    void testUdateBeer() {
        BeerDto beerDto = BeerDto.builder().beerStyle("New Beer").build();
        client.updateBeer(UUID.randomUUID(),beerDto);
    }

    @Test
    void testDelete() {
        client.deleteBeer(UUID.randomUUID());
    }


    @Test
    void getCustomerById() {

        CustomerDto customerDto = client.getCustomerById(UUID.randomUUID());
    }

    @Test
    void testSaveNewCustomer(){
        CustomerDto customerDto  = CustomerDto.builder().name("Jim").build();
        URI uri = client.saveNewCustomer(customerDto);
        assertNotNull(uri);
        System.out.println(uri.toString());
    }

    @Test
    void testUdateCustomer() {
        CustomerDto customerDto  = CustomerDto.builder().name("Jim").build();
        client.updateCustomer(UUID.randomUUID(),customerDto);
    }

    @Test
    void testDeleteCustomer() {
        client.deleteCustomer(UUID.randomUUID());
    }

}