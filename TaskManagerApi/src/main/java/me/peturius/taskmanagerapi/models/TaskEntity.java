package me.peturius.taskmanagerapi.models;

import me.peturius.taskmanagerapi.enums.taskenums.TaskPriorityEnum;
import me.peturius.taskmanagerapi.enums.taskenums.TaskStatusEnum;

import javax.persistence.*;


@Entity
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int ID;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false, columnDefinition = "ENUM('TODO', 'IN_PROGRESS', 'DONE')")
    @Enumerated(EnumType.STRING)
    private TaskStatusEnum status;
    @Column(nullable = false, columnDefinition = "ENUM('LOW', 'MEDIUM', 'HIGH')")
    @Enumerated(EnumType.STRING)
    private TaskPriorityEnum priority;



    public TaskEntity() {}

    public TaskEntity(String title, String description, TaskStatusEnum status, TaskPriorityEnum priority) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TaskStatusEnum status) {
        this.status = status;
    }

    public TaskPriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(TaskPriorityEnum priority) {
        this.priority = priority;
    }

}
