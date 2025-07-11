package com.example.task_manager_api.infrastructure.controller;


import com.example.task_manager_api.application.service.TaskService;
import com.example.task_manager_api.domain.model.Task;
import com.example.task_manager_api.infrastructure.controller.request.TaskRequest;
import com.example.task_manager_api.infrastructure.controller.response.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public Mono<TaskResponse> create(@RequestBody TaskRequest request) {
        Task task = new Task(null, request.getTitle(), false, request.getCategory());
        return taskService.create(task)
                .map(this::toResponse);
    }

    @GetMapping
    public Flux<TaskResponse> findAll() {
        return taskService.findAll()
                .map(this::toResponse);
    }

    @GetMapping("/{id}")
    public Mono<TaskResponse> findById(@PathVariable String id) {
        return taskService.findById(id)
                .map(this::toResponse);
    }

    @PatchMapping("/{id}/complete")
    public Mono<TaskResponse> complete(@PathVariable String id) {
        return taskService.complete(id)
                .map(this::toResponse);
    }

    @GetMapping("/category/{category}")
    public Flux<TaskResponse> findByCategory(@PathVariable String category) {
        return taskService.findByCategory(category)
                .map(this::toResponse);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return taskService.delete(id);
    }

    private TaskResponse toResponse(Task task) {
        TaskResponse res = new TaskResponse();
        res.setId(task.getId());
        res.setTitle(task.getTitle());
        res.setCompleted(task.isCompleted());
        res.setCategory(task.getCategory());
        return res;
    }
}
