package com.example.demo.service;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    //CRUD create read update delete


    public Task addTask(Task task){
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return taskRepository.save(task);
    }

    public List<Task> findAllTasks(){
        return (List<Task>) taskRepository.findAll();
    }

    public Task findById(String taskId){
        return taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("not found id"));
    }

    public List<Task> getTaskBySeverity(int severity) {
        return taskRepository.findBySeverity(severity);
    }

    public Task updateTask(Task taskRequest){
        Task existingTask=taskRepository.findById(taskRequest.getTaskId()).get();
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setAssignee(taskRequest.getAssignee());
        existingTask.setSeverity(taskRequest.getSeverity());
        existingTask.setStoryPoint(taskRequest.getStoryPoint());
        return taskRepository.save(existingTask);
    }

    public String deleteTask(String taskId)
    {
        taskRepository.deleteById(taskId);
        return "TaskId "+taskId+" is deleted.";
    }
}
