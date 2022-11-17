package me.peturius.taskmanagerapi.controllers;

import me.peturius.taskmanagerapi.exceptions.taskexceptions.TaskAlreadyAddedException;
import me.peturius.taskmanagerapi.exceptions.taskexceptions.TaskNotFoundException;
import me.peturius.taskmanagerapi.models.TaskEntity;
import me.peturius.taskmanagerapi.models.error.ErrorResponse;
import me.peturius.taskmanagerapi.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks/all")
    public List<TaskEntity> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/tasks/add")
    public TaskEntity addTask(@RequestBody TaskEntity task) {
        TaskEntity taskEntity = taskService.addTask(task);

        if (taskEntity == null) {
            throw new TaskAlreadyAddedException("Task with ID " + task.getID() + " already exists!");
        }

        return taskEntity;
    }

    @DeleteMapping("/tasks/delete/{id}")
    public TaskEntity deleteTask(@PathVariable int id) {
        TaskEntity taskEntity = taskService.deleteTask(id);

        if (taskEntity == null) {
            throw new TaskNotFoundException("Task not found with id: " + id);
        }

        return taskEntity;
    }

    @PutMapping("/tasks/update/{id}")
    public TaskEntity updateTask(@PathVariable int id, @RequestBody TaskEntity task) {
        TaskEntity taskEntity = taskService.updateTask(id, task);

        if(taskEntity == null) {
            throw new TaskNotFoundException("Task not found with id: " + id);
        }

        return taskEntity;
    }

    @ExceptionHandler(value = TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleTaskNotFoundException(TaskNotFoundException e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(value = TaskAlreadyAddedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleTaskAlreadyAddedException(TaskAlreadyAddedException e) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(),  e.getMessage());
    }

}
