package com.example.project.domain.exception;

public class ResourceAlreadyExistsException extends Exception {
    public ResourceAlreadyExistsException(Long resource) {
        super(resource + " already exists!");
    }
}
