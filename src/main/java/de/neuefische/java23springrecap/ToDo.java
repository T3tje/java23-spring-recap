package de.neuefische.java23springrecap;

import lombok.With;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("todo")
@With
public record ToDo (
        String id,
        String description,
        String status
){
}




