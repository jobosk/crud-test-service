package com.linkener.crudtest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "crud-test-service")
public class CrudTestServiceConfig {
}
