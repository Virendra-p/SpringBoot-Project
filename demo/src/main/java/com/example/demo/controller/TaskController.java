package com.example.demo.controller;


import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task)
    {
        return taskService.addTask(task);
    }

    @GetMapping
    public List<Task> getTask(){
        List<Task> taskList= taskService.findAllTasks();
        return taskList;
    }

    @GetMapping("/{taskId}")
    @ResponseStatus(HttpStatus.FOUND)
    public Task getTask(@PathVariable  String taskId){
       return  taskService.findById(taskId);
    }

    @GetMapping("/severity/{severity}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Task> findTaskBySeverity(@PathVariable int severity) {
        return taskService.getTaskBySeverity(severity);
    }

    @PutMapping
    public Task modifyTask(@RequestBody Task taskRequest){
        return taskService.updateTask(taskRequest);
    }

    @DeleteMapping("{taskId}")
    public String deleteTask(@PathVariable String taskId)
    {
        return  taskService.deleteTask(taskId);
    }

}
