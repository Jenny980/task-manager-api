package com.example.task_manager_api.infrastructure.controller.response;

import lombok.Data;

@Data
public class TaskResponse {
    private String id;
    private String title;
    private boolean completed;
    private String category;
}
