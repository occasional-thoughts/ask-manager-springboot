package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setStatus("Pending");
        task.setCreatedAt(java.time.LocalDateTime.now());
        return taskRepository.save(task);
    }

    @PutMapping("/{id}/done")
    public Task markTaskAsDone(@PathVariable Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setStatus("Done");
        return taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}
