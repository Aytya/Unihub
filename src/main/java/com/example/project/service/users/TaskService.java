package com.example.project.service.users;

import com.example.project.domain.model.Task;
import com.example.project.domain.exception.ResourceDoesNotExistException;

import java.time.Duration;
import java.util.List;

public interface TaskService {

    Task getById(Long id) throws ResourceDoesNotExistException;

    List<Task> getAllByUserId(Long id);

    List<Task> getAllSoonTasks(Duration duration);

    Task update(Task task) throws ResourceDoesNotExistException;

    Task create(Task task);

    void delete(Long id);

}