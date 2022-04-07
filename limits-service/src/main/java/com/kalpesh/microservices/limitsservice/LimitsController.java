package com.kalpesh.microservices.limitsservice;

import com.kalpesh.microservices.limitsservice.bean.Limits;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    @HystrixCommand
    public Limits retrievelLimitsFromConfigurations() {
        return new Limits(configuration.getMaximum(), configuration.getMinimum());
    }

    @GetMapping("/fault-tolerance-example")
    @HystrixCommand(fallbackMethod = "fallbackRetrieveConfiguration")
    public Limits retrievelConfigurations() {
        throw new RuntimeException("Not available");
    }

    public Limits fallbackRetrieveConfiguration() {
        return new Limits(1, 9);
    }
}
