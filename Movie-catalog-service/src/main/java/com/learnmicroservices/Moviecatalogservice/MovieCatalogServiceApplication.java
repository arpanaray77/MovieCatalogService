package com.learnmicroservices.Moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class MovieCatalogServiceApplication {
   
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}
	
	@Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
		
//		//adding timeout using Rest Template
//		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//		clientHttpRequestFactory.setConnectTimeout(3000);
//		
//        return new RestTemplate(clientHttpRequestFactory);
		
		return new RestTemplate();
    }
	 
}
