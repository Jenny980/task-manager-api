package com.example.task_manager_api.application.service;


import com.example.task_manager_api.domain.model.Task;
import com.example.task_manager_api.domain.repository.ITaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

public class TaskServiceTest {

    private ITaskRepository taskRepository;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepository = mock(ITaskRepository.class);
        taskService = new TaskService(taskRepository);
    }

    @Test
    void shouldCreateTask() {
        Task newTask = new Task(null, "Test Task", false, "Work");
        Task savedTask = new Task("1", "Test Task", false, "Work");

        when(taskRepository.save(any(Task.class))).thenReturn(Mono.just(savedTask));

        StepVerifier.create(taskService.create(newTask))
                .expectNext(savedTask)
                .verifyComplete();

        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void shouldFindAllTasks() {
        Task t1 = new Task("1", "Task 1", false, "Work");
        Task t2 = new Task("2", "Task 2", true, "Personal");

        when(taskRepository.findAll()).thenReturn(Flux.just(t1, t2));

        StepVerifier.create(taskService.findAll())
                .expectNext(t1)
                .expectNext(t2)
                .verifyComplete();

        verify(taskRepository).findAll();
    }

    @Test
    void shouldFindTaskById() {
        Task task = new Task("1", "Task 1", false, "Work");

        when(taskRepository.findById("1")).thenReturn(Mono.just(task));

        StepVerifier.create(taskService.findById("1"))
                .expectNext(task)
                .verifyComplete();

        verify(taskRepository).findById("1");
    }

    @Test
    void shouldCompleteTask() {
        Task completedTask = new Task("1", "Task 1", true, "Work");

        when(taskRepository.complete("1")).thenReturn(Mono.just(completedTask));

        StepVerifier.create(taskService.complete("1"))
                .expectNext(completedTask)
                .verifyComplete();

        verify(taskRepository).complete("1");
    }

    @Test
    void shouldDeleteTask() {
        when(taskRepository.delete("1")).thenReturn(Mono.empty());

        StepVerifier.create(taskService.delete("1"))
                .verifyComplete();

        verify(taskRepository).delete("1");
    }

    @Test
    void shouldFindByCategory() {
        Task t1 = new Task("1", "Task 1", false, "Work");
        Task t2 = new Task("2", "Task 2", false, "Work");

        when(taskRepository.findByCategory("Work")).thenReturn(Flux.just(t1, t2));

        StepVerifier.create(taskService.findByCategory("Work"))
                .expectNext(t1)
                .expectNext(t2)
                .verifyComplete();

        verify(taskRepository).findByCategory("Work");
    }
}