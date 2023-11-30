package de.neuefische.java23springrecap;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.config.CorsRegistry;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService service;
    @GetMapping
    public List<ToDo> getAllToDos() {
        return service.getAllToDos();
    }

    @GetMapping("/{id}")
    public ToDo getToDoById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public ToDo saveToDo(@RequestBody ToDo todo) {
        return service.saveToDo(todo);
    }

    @PutMapping("/{id}")
    public ToDo updateToDo (@PathVariable String id, @RequestBody ToDo todo) {
        return service.updateToDo(id,todo);
    }

    @DeleteMapping("/{id}")
    public void deleteToDo(@PathVariable String id) {
        service.deleteToDo(id);
    }
}
