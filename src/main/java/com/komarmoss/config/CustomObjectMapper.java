package com.komarmoss.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;

import java.io.IOException;

public class CustomObjectMapper extends ObjectMapper {
    private static final String EMPTY_STR_RPL = "-";
    private static CustomObjectMapper instance;

    private CustomObjectMapper() {
        super();
        DefaultSerializerProvider.Impl sp = new DefaultSerializerProvider.Impl();
        sp.setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString(EMPTY_STR_RPL);
            }
        });
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        setSerializerProvider(sp);
    }

    public static synchronized CustomObjectMapper getInstance() {
        if (instance == null)
            instance = new CustomObjectMapper();
        return instance;
    }

}
