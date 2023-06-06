package com.albaco.depositorigination.common.utils.serialisers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ListToCsvSerializer extends JsonSerializer<List<Enum>> {
    @Override
    public void serialize(List<Enum> enumList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(enumList.stream().map(Enum::name).collect(Collectors.joining(",")));
    }
}
