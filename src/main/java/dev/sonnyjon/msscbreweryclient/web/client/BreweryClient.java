package dev.sonnyjon.msscbreweryclient.web.client;

import dev.sonnyjon.msscbreweryclient.web.dto.BeerDto;
import dev.sonnyjon.msscbreweryclient.web.dto.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

/**
 * Created by Sonny on 8/22/2022.
 */
@Component
@ConfigurationProperties(value = "sj.brewery", ignoreUnknownFields = false)
public class BreweryClient
{
    public static final String V1_BEER_PATH = "/api/v1/beer/";
    public static final String V1_CUSTOMER_PATH = "/api/v1/customer/";
    private final RestTemplate restTemplate;

    private String apihost;

    public BreweryClient(RestTemplateBuilder builder)
    {
        this.restTemplate = builder.build();
    }

    public BeerDto getBeerById(UUID uuid)
    {
        return restTemplate.getForObject(apihost + V1_BEER_PATH + uuid.toString(), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beerDto)
    {
        return restTemplate.postForLocation(apihost + V1_BEER_PATH, beerDto);
    }

    public void updateBeer(BeerDto beerDto)
    {
        restTemplate.put(apihost + V1_BEER_PATH + beerDto.getId().toString(), beerDto);
    }

    public void deleteBeer(UUID uuid)
    {
        restTemplate.delete(apihost + V1_BEER_PATH + uuid.toString());
    }

    public CustomerDto getCustomerById(UUID uuid)
    {
        return restTemplate.getForObject(apihost + V1_CUSTOMER_PATH + uuid.toString(), CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto CustomerDto)
    {
        return restTemplate.postForLocation(apihost + V1_CUSTOMER_PATH, CustomerDto);
    }

    public void updateCustomer(CustomerDto CustomerDto)
    {
        restTemplate.put(apihost + V1_CUSTOMER_PATH + CustomerDto.getId().toString(), CustomerDto);
    }

    public void deleteCustomer(UUID uuid)
    {
        restTemplate.delete(apihost + V1_CUSTOMER_PATH + uuid.toString());
    }

    public void setApihost(String apihost)
    {
        this.apihost = apihost;
    }
}
