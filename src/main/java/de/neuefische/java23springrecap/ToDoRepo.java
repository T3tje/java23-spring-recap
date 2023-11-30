package de.neuefische.java23springrecap;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepo extends MongoRepository<ToDo, String> {
}