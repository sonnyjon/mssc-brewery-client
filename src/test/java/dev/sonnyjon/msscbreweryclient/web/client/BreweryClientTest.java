package dev.sonnyjon.msscbreweryclient.web.client;

import dev.sonnyjon.msscbreweryclient.web.dto.BeerDto;
import dev.sonnyjon.msscbreweryclient.web.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by Sonny on 8/22/2022.
 */
@Slf4j
@SpringBootTest
class BreweryClientTest
{
    @Autowired
    BreweryClient client;

    @Test
    void givenUUID_whenGetBeerById_thenBeerDto()
    {
        BeerDto dto = client.getBeerById( UUID.randomUUID() );
        assertNotNull( dto );
    }

    @Test
    void givenBeerDto_whenSaveNewBeer_thenUri()
    {
        // given
        BeerDto testBeer = BeerDto.builder().name( "New Beer" ).build();

        // when
        URI uri = client.saveNewBeer( testBeer );

        // then
        assertNotNull( uri );
        log.info( "URI: " + uri );
    }

    @Test
    void givenBeerDto_whenUpdateBeer_thenPutMethod()
    {
        // given
        BeerDto testBeer = BeerDto.builder()
                                .id( UUID.randomUUID() )
                                .name( "Some Beer" )
                                .build();

        // when, then
        client.updateBeer( testBeer );
    }

    @Test
    void givenUUID_whenDeleteBeer_thenDeleteMethod()
    {
        client.deleteBeer( UUID.randomUUID() );
    }

    @Test
    void givenUUID_whenGetCustomerById_thenCustomerDto()
    {
        CustomerDto dto = client.getCustomerById( UUID.randomUUID() );
        assertNotNull( dto );
    }

    @Test
    void givenCustomerDto_whenSaveNewCustomer_thenUri()
    {
        // given
        CustomerDto testCustomer = CustomerDto.builder().name( "New Customer" ).build();

        // when
        URI uri = client.saveNewCustomer( testCustomer );

        // then
        assertNotNull( uri );
        log.info( "URI: " + uri );
    }

    @Test
    void givenCustomerDto_whenUpdateCustomer_thenPutMethod()
    {
        // given
        CustomerDto testCustomer = CustomerDto.builder()
                .id( UUID.randomUUID() )
                .name( "Some Customer" )
                .build();

        // when, then
        client.updateCustomer( testCustomer );
    }

    @Test
    void givenUUID_whenDeleteCustomer_thenDeleteMethod()
    {
        client.deleteCustomer( UUID.randomUUID() );
    }
}