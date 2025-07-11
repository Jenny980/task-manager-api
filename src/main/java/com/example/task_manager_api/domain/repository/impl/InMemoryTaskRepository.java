package com.example.task_manager_api.domain.repository.impl;
import com.example.task_manager_api.domain.model.Task;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import com.example.task_manager_api.domain.repository.ITaskRepository;

@Repository
public class InMemoryTaskRepository implements ITaskRepository {

    private final Map<String, Task> store = new ConcurrentHashMap<>();

    @Override
    public Mono<Task> save(Task task) {
        String id = UUID.randomUUID().toString();
        task.setId(id);
        task.setCompleted(false);
        store.put(id, task);
        return Mono.just(task);
    }

    @Override
    public Flux<Task> findAll() {
        return Flux.fromIterable(store.values());
    }

    @Override
    public Mono<Task> findById(String id) {
        return Mono.justOrEmpty(store.get(id));
    }

    @Override
    public Mono<Task> complete(String id) {
        Task task = store.get(id);
        if (task != null) {
            task.setCompleted(true);
            return Mono.just(task);
        }
        return Mono.empty();
    }

    @Override
    public Mono<Void> delete(String id) {
        store.remove(id);
        return Mono.empty();
    }

    @Override
    public Flux<Task> findByCategory(String category) {
        return Flux.fromStream(store.values().stream()
                .filter(task -> category.equals(task.getCategory())));
    }
}
