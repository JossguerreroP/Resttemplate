package com.app.resttemplate.web.config;


import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;




@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {


    public ClientHttpRequestFactory clientHttpRequestFactory() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
   restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}
