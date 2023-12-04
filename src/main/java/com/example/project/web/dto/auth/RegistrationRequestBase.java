package com.example.project.web.dto.auth;

import com.example.project.domain.role.Role;

public interface RegistrationRequestBase {
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPassword();
}