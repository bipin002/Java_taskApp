package com.taskfusion.taskApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskCheck {
    @GetMapping("/TaskCheckStatus")
    public String isTaskRunning(){
        return "Ok";
    }
}
