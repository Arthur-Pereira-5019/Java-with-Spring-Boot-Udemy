package com.arthur_pereira.custom_json_serialization.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class GenderSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String gender, JsonGenerator jsg, SerializerProvider serializerProvider) throws IOException {
        String formatedGender = "Male".equals(gender) ? "M" : "F";
        jsg.writeString(formatedGender);
    }
}
