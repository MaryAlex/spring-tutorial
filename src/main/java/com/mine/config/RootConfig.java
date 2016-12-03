package com.mine.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"com.mine"}, // There must be your package with all java classes. (Briefly)
        excludeFilters = {@Filter(value = EnableWebMvc.class)})
public class RootConfig {
}
