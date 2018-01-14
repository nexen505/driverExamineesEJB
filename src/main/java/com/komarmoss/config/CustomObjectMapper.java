package com.komarmoss.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;

public class CustomObjectMapper extends ObjectMapper {
    private static final String EMPTY_STR_RPL = "-";
    private static CustomObjectMapper instance;

    private CustomObjectMapper() {
        super();
        DefaultSerializerProvider.Impl sp = new DefaultSerializerProvider.Impl();
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        setSerializerProvider(sp);
    }

    public static synchronized CustomObjectMapper getInstance() {
        if (instance == null)
            instance = new CustomObjectMapper();
        return instance;
    }

}
