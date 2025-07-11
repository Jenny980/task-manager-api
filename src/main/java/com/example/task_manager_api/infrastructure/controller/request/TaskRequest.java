package com.example.task_manager_api.infrastructure.controller.request;

import lombok.Data;

@Data
public class TaskRequest {
    private String title;
    private String category;
}
