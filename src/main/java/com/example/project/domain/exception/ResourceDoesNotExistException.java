package com.example.project.domain.exception;

public class ResourceDoesNotExistException extends Exception {
    public ResourceDoesNotExistException(Long resource) {
        super(resource + " does not exist!");
    }
}