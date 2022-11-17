package me.peturius.taskmanagerapi.exceptions.taskexceptions;

public class TaskAlreadyAddedException extends RuntimeException {

    public TaskAlreadyAddedException(String message) {
        super(message);
    }

}
