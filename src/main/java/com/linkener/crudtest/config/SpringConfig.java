package com.linkener.crudtest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.linkener.crudtest", "com.belike.core"})
@EnableTransactionManagement
public class SpringConfig {

}
