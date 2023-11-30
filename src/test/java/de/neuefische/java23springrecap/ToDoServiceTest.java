package de.neuefische.java23springrecap;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToDoServiceTest {

    private final ToDoRepo toDoRepositoryMocked = mock(ToDoRepo.class);
    @Test
    void getAllToDos_whenExecuted_thenReturnTheDefinedList() {
        //GIVEN
        List<ToDo> exampleToDoList = List.of(new ToDo("1234", "Tun", "IN_PROGRESS"), new ToDo("5432", "Machen", "OPEN"));
        when(toDoRepositoryMocked.findAll()).thenReturn(exampleToDoList);
        ToDoService toDoService1 = new ToDoService(toDoRepositoryMocked);
        //WHEN
        List<ToDo> actual = toDoService1.getAllToDos();
        //THEN
        verify(toDoRepositoryMocked).findAll();
        assertEquals(exampleToDoList, actual);
    }

    @Test
    void saveToDo_whenInsertAToDo_thenTheToDoShouldBeReturned() {
        //GIVEN
        ToDo expectedToDo = new ToDo("1234", "machen", "DONE");
        when(toDoRepositoryMocked.save(expectedToDo)).thenReturn(expectedToDo);
        ToDoService toDoService1 = new ToDoService(toDoRepositoryMocked);
        //WHEN
        toDoService1.saveToDo(expectedToDo);
        //THEN
        verify(toDoRepositoryMocked).save(expectedToDo);

    }
    //FIND BY ID
    @Test
    void findById_whenExecutedMitId_shouldReturnAToDo() {
        //GIVEN
        ToDo expectedToDo = new ToDo("1234", "machen", "DONE");
        when(toDoRepositoryMocked.findById("1234")).thenReturn(Optional.of(expectedToDo));
        ToDoService toDoService1 = new ToDoService(toDoRepositoryMocked);
        //WHEN
        ToDo actual = toDoService1.findById("1234");

        //THEN
        verify(toDoRepositoryMocked).findById("1234");
        assertEquals(expectedToDo, actual);
    }

    @Test
    void findById_whenExecutedWithInvalidId_shouldThrowNoSuchElementException() {
        //GIVEN
        when(toDoRepositoryMocked.findById(any())).thenReturn(Optional.empty());
        ToDoService toDoService1 = new ToDoService(toDoRepositoryMocked);
        //WHEN
        //THEN
        try {
            toDoService1.findById("1234");
            fail();
        } catch (NoSuchElementException e) {
            verify(toDoRepositoryMocked).findById("1234");
            assertTrue(true);
        }
    }

    //UPDATERS TESTS
    @Test
    void updateToDo_whenInsertAToDo_thenTheToDoShouldBeReturned() {
        //GIVEN
        ToDo expectedToDo = new ToDo("1234", "machen", "DONE");
        when(toDoRepositoryMocked.findById("1234")).thenReturn(Optional.of(expectedToDo));
        when(toDoRepositoryMocked.save(expectedToDo)).thenReturn(expectedToDo);
        ToDoService toDoService1 = new ToDoService(toDoRepositoryMocked);
        //WHEN
        ToDo actual = toDoService1.updateToDo("1234", expectedToDo);
        //THEN
        verify(toDoRepositoryMocked).save(expectedToDo);
        assertEquals(expectedToDo, actual);
    }

    @Test
    void deleteToDo_whenUpdateAnNotExistingToDo_thenThrowNoSuchElementException () {
        //GIVEN
        ToDo exampleToDo = new ToDo("1234", "machen", "DONE");
        when(toDoRepositoryMocked.findById(any())).thenReturn(Optional.empty());
        ToDoService toDoService1 = new ToDoService(toDoRepositoryMocked);
        //WHEN
        //THEN
        try {
            toDoService1.updateToDo("1234", exampleToDo);
            fail();
        } catch (NoSuchElementException e) {
            verify(toDoRepositoryMocked).findById("1234");
            assertTrue(true);
        }
    }

    //DELETE TESTS
    @Test
    void deleteToDo_whenExecuteWithValidId_shouldReturnStringSucces() {
        //GIVEN
        ToDo exampleToDo = new ToDo("1234", "machen", "DONE");
        when(toDoRepositoryMocked.findById("1234")).thenReturn(Optional.of(exampleToDo));
        ToDoService toDoService1 = new ToDoService(toDoRepositoryMocked);
        //WHEN
        toDoService1.deleteToDo("1234");
        //THEN
        verify(toDoRepositoryMocked).findById("1234");
        verify(toDoRepositoryMocked).delete(exampleToDo);
    }

    @Test
    void deleteToDo_whenExecuteWithInvalidId_ShouldThrowNoSuchElementException() {
        //GIVEN
        ToDo exampleToDo = new ToDo("1234", "machen", "DONE");
        when(toDoRepositoryMocked.findById("1234")).thenReturn(Optional.of(exampleToDo));
        ToDoService toDoService1 = new ToDoService(toDoRepositoryMocked);
        //WHEN
        //THEN
        try {
            toDoService1.deleteToDo("123");
            fail();
        } catch (Exception e){
            verify(toDoRepositoryMocked).findById("123");
            assertTrue(true);
        }
    }
}