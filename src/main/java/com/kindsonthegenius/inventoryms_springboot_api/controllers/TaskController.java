package com.kindsonthegenius.inventoryms_springboot_api.controllers;

import com.kindsonthegenius.inventoryms_springboot_api.models.Task;
import com.kindsonthegenius.inventoryms_springboot_api.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> getTaskes(){
        return taskService.getAllTasks();
    }

    @GetMapping("/task/{id}")
    public Task getTask(@PathVariable("id") Long id){
        return taskService.getTaskById(id);
    }

    @PutMapping("/task/{id}")
    public Task updateTask(@RequestBody() Task task, @PathVariable("id") Long id){
        return taskService.save(task);
    }

    @PostMapping("/tasks")
    public Task addNew(@RequestBody() Task task){
        return taskService.save(task);
    }

    @DeleteMapping("/task/{id}")
    public void deleteTask(@PathVariable("id") Long id){
        taskService.deleteTask(id);
    }
}

