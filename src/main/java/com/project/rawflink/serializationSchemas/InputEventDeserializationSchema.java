package com.project.rawflink.serializationSchemas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.rawflink.events.InputEvent;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

public class InputEventDeserializationSchema implements DeserializationSchema<InputEvent> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public InputEvent deserialize(byte[] bytes) throws IOException {
        try {
            return objectMapper.readValue(bytes, InputEvent.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean isEndOfStream(InputEvent inputEvent) {
        return false;
    }

    @Override
    public TypeInformation<InputEvent> getProducedType() {
        return TypeInformation.of(InputEvent.class);
    }
}
