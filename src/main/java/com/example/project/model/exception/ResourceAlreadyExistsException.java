package com.example.project.model.exception;

public class ResourceAlreadyExistsException extends Exception {
    public ResourceAlreadyExistsException(Long resource) {
        super(resource + " already exists!");
    }
}
