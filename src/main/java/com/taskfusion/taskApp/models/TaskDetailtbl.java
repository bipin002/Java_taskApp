package com.taskfusion.taskApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.util.Date;

@Entity
public class TaskDetailtbl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String task_Name;
    private String task_Details;
    private Date task_DateTime;
    private Boolean status;
    private Date task_updatedDate;

    public Date getTask_updatedDate() {
        return task_updatedDate;
    }

    public void setTask_updatedDate(Date task_updatedDate) {
        this.task_updatedDate = task_updatedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask_Name() {
        return task_Name;
    }

    public void setTask_Name(String task_Name) {
        this.task_Name = task_Name;
    }

    public String getTask_Details() {
        return task_Details;
    }

    public void setTask_Details(String task_Details) {
        this.task_Details = task_Details;
    }

    public Date getTask_DateTime() {
        return task_DateTime;
    }

    public void setTask_DateTime(Date task_DateTime) {
        this.task_DateTime = task_DateTime; //LocalDateTime.now();
    }

    public Boolean getStatus() {
        return true;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
