package com.example.task_manager_api.domain.repository;

import com.example.task_manager_api.domain.model.Task;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITaskRepository {
    Mono<Task> save(Task task);
    Flux<Task> findAll();
    Mono<Task> findById(String id);
    Mono<Task> complete(String id);
    Mono<Void> delete(String id);
    Flux<Task> findByCategory(String category);
}
