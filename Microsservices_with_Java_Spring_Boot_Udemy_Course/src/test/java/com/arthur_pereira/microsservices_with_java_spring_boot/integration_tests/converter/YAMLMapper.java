package com.arthur_pereira.microsservices_with_java_spring_boot.integration_tests.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;

public class YAMLMapper implements ObjectMapper {

    private com.fasterxml.jackson.databind.ObjectMapper objectMapper;
    protected com.fasterxml.jackson.databind.type.TypeFactory typeFactory;

    @Override
    public Object deserialize(ObjectMapperDeserializationContext context) {
        var content = context.getDataToDeserialize().asString();
        Class type = (Class) context.getType();
        try {
            objectMapper.readValue(content, typeFactory.constructType(type));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error deserializing YAML content",e);
        }
        return null;
    }

    @Override
    public Object serialize(ObjectMapperSerializationContext context) {
        try {
            objectMapper.writeValueAsString(context.getObjectToSerialize());
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error serializing content to YAML",e);
        }

        return null;
    }

    public YAMLMapper() {
        objectMapper = new com.fasterxml.jackson.databind.ObjectMapper(new YAMLFactory());
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        typeFactory = TypeFactory.defaultInstance();
    }
}
