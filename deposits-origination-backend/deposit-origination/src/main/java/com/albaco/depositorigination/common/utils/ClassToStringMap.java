package com.albaco.depositorigination.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public interface ClassToStringMap {

    public final static ObjectMapper objectMapper = new ObjectMapper();

    public default Map<String, String> toMap() {
        return objectMapper.convertValue(this, new TypeReference<Map<String, String>>() {
        });
    }
}
