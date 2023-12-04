package com.example.project.web.dto.auth;

import com.example.project.domain.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest implements RegistrationRequestBase{

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
