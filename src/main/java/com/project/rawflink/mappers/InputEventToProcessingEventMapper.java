package com.project.rawflink.mappers;

import com.project.rawflink.events.InputEvent;
import com.project.rawflink.events.ProcessingEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@Mapper(componentModel = "spring")
public interface InputEventToProcessingEventMapper extends Serializable {
    @Mapping(source = "id", target = "itemId")
    @Mapping(source = ".", target = "newId", qualifiedByName = "calculateNewId")
    @Mapping(source = "content", target = "content")
    ProcessingEvent toProcessingEvent(InputEvent inputEvent);

    @Named("calculateNewId")
    default String calculateNewId(InputEvent inputEvent) {
        return String.valueOf(inputEvent.getId().hashCode());
    }
}
