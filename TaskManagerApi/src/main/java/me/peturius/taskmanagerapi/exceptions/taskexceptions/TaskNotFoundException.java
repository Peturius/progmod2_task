package me.peturius.taskmanagerapi.exceptions.taskexceptions;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String message) {
        super(message);
    }

}
