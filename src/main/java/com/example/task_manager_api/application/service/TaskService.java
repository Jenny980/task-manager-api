package com.example.task_manager_api.application.service;

import com.example.task_manager_api.domain.model.Task;
import com.example.task_manager_api.domain.repository.ITaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final ITaskRepository repository;

    public Mono<Task> create(Task task) {
        return repository.save(task);
    }

    public Flux<Task> findAll() {
        return repository.findAll();
    }

    public Mono<Task> findById(String id) {
        return repository.findById(id);
    }

    public Mono<Task> complete(String id) {
        return repository.complete(id);
    }

    public Mono<Void> delete(String id) {
        return repository.delete(id);
    }

    public Flux<Task> findByCategory(String category) {
        return repository.findByCategory(category);
    }
}
