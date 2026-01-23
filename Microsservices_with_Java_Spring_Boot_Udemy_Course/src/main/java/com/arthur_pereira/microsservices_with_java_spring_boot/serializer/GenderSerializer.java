package com.arthur_pereira.microsservices_with_java_spring_boot.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Objects;

public class GenderSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String gender, JsonGenerator jsg, SerializerProvider serializerProvider) throws IOException {
        String formatedGender;
        if(Objects.equals(gender, "Male") || Objects.equals(gender, "M") || Objects.equals(gender, "male")) {
            formatedGender = "M";
        } else {
            formatedGender = "F";
        }
        jsg.writeString(formatedGender);
    }
}
