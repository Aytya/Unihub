package com.example.project.service.impl;

import com.example.project.domain.role.Status;
import com.example.project.domain.model.Task;
import com.example.project.domain.exception.ResourceDoesNotExistException;
import com.example.project.repository.TaskRepository;
import com.example.project.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskDAO implements TaskService {

    @Autowired
    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public Task getById(final Long id) throws ResourceDoesNotExistException {
        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceDoesNotExistException(id));
    }

    @Override
    public List<Task> getAllByUserId(final Long id) {
        return taskRepository.findAllByUserId(id);
    }

    @Override
    public List<Task> getAllSoonTasks(final Duration duration) {
        LocalDateTime now = LocalDateTime.now();
        return taskRepository.findAllSoonTasks(Timestamp.valueOf(now),
                Timestamp.valueOf(now.plus(duration)));
    }

    @Override
    public Task update(final Task task) throws ResourceDoesNotExistException {
        Task existing = getById(task.getId());
        if (task.getStatus() == null) {
            existing.setStatus(Status.TODO);
        } else {
            existing.setStatus(task.getStatus());
        }
        existing.setTitle(task.getTitle());
        existing.setDescription(task.getDescription());
        existing.setExpirationDate(task.getExpirationDate());
        taskRepository.save(task);
        return task;
    }

    @Override
    @Transactional
    public Task create(final Task task) {
        if (task.getStatus() != null) {
            task.setStatus(Status.TODO);
        }
        taskRepository.save(task);
//        taskRepository.assignTask(userId, task.getId());
        return task;
    }

    @Override
    @Transactional
    public void delete(final Long id) {
        taskRepository.deleteById(id);
    }

}
