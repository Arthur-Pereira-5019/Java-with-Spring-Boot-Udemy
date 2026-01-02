package com.arthur_pereira.microsservices_with_java_spring_boot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        SimpleFilterProvider filter = new SimpleFilterProvider().addFilter("PersonFilter", SimpleBeanPropertyFilter.serializeAllExcept("gender"));
        mapper.setFilterProvider(filter);

    return mapper;
    }
}
