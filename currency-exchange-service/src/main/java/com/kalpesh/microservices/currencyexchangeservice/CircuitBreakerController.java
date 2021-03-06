package com.kalpesh.microservices.currencyexchangeservice;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
//    @Retry(name = "default", fallbackMethod = "hardCodedResponse")
    @CircuitBreaker(name = "default", fallbackMethod = "hardCodedResponse")
    public String sampleApi() {
        logger.info("Sample API Call received");
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost:8080/Some dummy url", String.class);
        return responseEntity.getBody();
    }

    public String hardCodedResponse(Exception e) {
        return "fallback-response";
    }
}
