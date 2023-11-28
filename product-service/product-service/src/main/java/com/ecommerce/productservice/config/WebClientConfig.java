package com.ecommerce.productservice.config;


import com.ecommerce.productservice.client.CategoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;


@Configuration
public class WebClientConfig {


    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;



    @Bean
    public WebClient categoryWebClient(){
        return WebClient.builder()
                .baseUrl("http://category-service")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public CategoryClient CategoryClient() {
        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(categoryWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(CategoryClient.class);
    }
}
