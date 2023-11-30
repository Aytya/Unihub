package com.example.project.web.controller;

import com.example.project.domain.exception.ResourceDoesNotExistException;
import com.example.project.domain.model.Task;
import com.example.project.service.TaskService;
import com.example.project.web.dto.TaskDto;
import com.example.project.web.mapper.TaskMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Task Controller", description = "Task API")
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('admin:create')")
    public Task create(@RequestBody final Task task){
        return taskService.create(task);
    }

    @PutMapping
    @Operation(summary = "Update task")
    @PreAuthorize("hasAuthority('admin:update')")
    public TaskDto update(@RequestBody final TaskDto dto) throws ResourceDoesNotExistException {
        Task task = taskMapper.toEntity(dto);
        Task updatedTask = taskService.update(task);
        return taskMapper.toDto(updatedTask);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get TaskDto by id")
    public TaskDto getById(@PathVariable final Long id) throws ResourceDoesNotExistException {
        Task task = taskService.getById(id);
        return taskMapper.toDto(task);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task")
//    @PreAuthorize("hasRole('admin:delete")")
    public void deleteById(@PathVariable final Long id) {
        taskService.delete(id);
    }

}
