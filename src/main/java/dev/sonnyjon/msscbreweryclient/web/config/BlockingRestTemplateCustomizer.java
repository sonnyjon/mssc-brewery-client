package dev.sonnyjon.msscbreweryclient.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Sonny on 8/22/2022.
 */
@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer
{
    private final Integer maxTotalConnections;
    private final Integer defaultMaxTotalConnections;
    private final Integer connectionRequestTimeout;
    private final Integer socketTimeOut;

    public BlockingRestTemplateCustomizer(@Value("${pub.maxtotalconnections}") Integer maxTotalConnections,
                                          @Value("${pub.defaultmaxtotalconnections}") Integer defaultMaxTotalConnections,
                                          @Value("${pub.connectionrequesttimeout}") Integer connectionRequestTimeout,
                                          @Value("${pub.sockettimeout}") Integer socketTimeOut)
    {
        this.maxTotalConnections = maxTotalConnections;
        this.defaultMaxTotalConnections = defaultMaxTotalConnections;
        this.connectionRequestTimeout = connectionRequestTimeout;
        this.socketTimeOut = socketTimeOut;
    }

    public ClientHttpRequestFactory clientHttpRequestFactory()
    {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal( maxTotalConnections );
        connectionManager.setDefaultMaxPerRoute( defaultMaxTotalConnections );

        RequestConfig requestConfig = RequestConfig
                                            .custom()
                                            .setConnectionRequestTimeout( connectionRequestTimeout )
                                            .setSocketTimeout( socketTimeOut )
                                            .build();

        CloseableHttpClient httpClient = HttpClients
                                            .custom()
                                            .setConnectionManager( connectionManager )
                                            .setKeepAliveStrategy( new DefaultConnectionKeepAliveStrategy() )
                                            .setDefaultRequestConfig( requestConfig )
                                            .build();

        return new HttpComponentsClientHttpRequestFactory( httpClient );
    }

    @Override
    public void customize(RestTemplate restTemplate)
    {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}