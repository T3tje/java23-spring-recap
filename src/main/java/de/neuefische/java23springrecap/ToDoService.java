package de.neuefische.java23springrecap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepo repo;

    public List<ToDo> getAllToDos() {
        return repo.findAll();
    }

    public ToDo saveToDo(ToDo todo) {
        return repo.save(new ToDo(UUID.randomUUID().toString(), todo.description(), todo.status()));
    }

    public ToDo findById(String id) {
        Optional<ToDo> oldToDo = repo.findById(id);
        if(oldToDo.isPresent()) {
            return oldToDo.get();
        } else {
            throw new NoSuchElementException("ToDo not found with ID: " + id);
        }
    }

    public ToDo updateToDo (String id,ToDo todo) {
        Optional<ToDo> oldToDo = repo.findById(id);
                if(oldToDo.isPresent()) {
                    ToDo newToDo = oldToDo.get().withStatus(todo.status()).withDescription(todo.description());
                    return repo.save(newToDo);
                } else {
                    throw new NoSuchElementException("Element mit der id: " +id+ " nicht gefunden");
                }

    }

    public void deleteToDo(String id) {
        Optional<ToDo> toDoToDeleteOptional = repo.findById(id);
        if (toDoToDeleteOptional.isPresent()) {
            ToDo toDoToDelete = toDoToDeleteOptional.get();
            repo.delete(toDoToDelete);
        }
    }



}
