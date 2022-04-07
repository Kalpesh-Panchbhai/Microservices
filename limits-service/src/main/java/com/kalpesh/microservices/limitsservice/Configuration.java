package com.kalpesh.microservices.limitsservice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("limits-service")
@Component
@Getter
@Setter
public class Configuration {

    private int minimum;

    private int maximum;
}
