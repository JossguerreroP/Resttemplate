package com.app.resttemplate.web.config;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    private final Integer maxTotalConnections;
    private final Integer defaultMaxTotalConnetions;
    private final Integer connectionRequestTimeout;
    private final Integer socketTimeout;

    public BlockingRestTemplateCustomizer(@Value("${sfg.maxtotalconnections}") Integer maxTotalConnections,
                                          @Value("${sfg.defaultmaxtotalconnections}") Integer defaultMaxTotalConnetions,
                                          @Value("${sfg.connectionrequesttimeout}")Integer connectionRequestTimeout,
                                          @Value("${sfg.sockettimeout}")Integer socketTimeout) {
        this.maxTotalConnections = maxTotalConnections;
        this.defaultMaxTotalConnetions = defaultMaxTotalConnetions;
        this.connectionRequestTimeout = connectionRequestTimeout;
        this.socketTimeout = socketTimeout;
    }

    public ClientHttpRequestFactory clientHttpRequestFactory() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotalConnections);
        connectionManager.setDefaultMaxPerRoute(defaultMaxTotalConnetions);

        var requestConfig = org.apache.hc.client5.http.config.RequestConfig
                .custom()
                .setConnectionRequestTimeout(Timeout.ofMilliseconds(defaultMaxTotalConnetions))
                .setResponseTimeout(Timeout.ofMilliseconds(connectionRequestTimeout))
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    //    public ClientHttpRequestFactory clientHttpRequestFactory() {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        return new HttpComponentsClientHttpRequestFactory(httpClient);
//    }

    @Override
    public void customize(RestTemplate restTemplate) {
   restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}
