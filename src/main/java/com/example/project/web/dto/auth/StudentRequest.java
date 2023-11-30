package com.example.project.web.dto.auth;

import com.example.project.domain.role.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    public String department;
    public String program;
    public String yearOfSubmission;
    public String dateOfBirth;
    public String idNo;
    public String permanentAddress;
    public String phone;
    public String nationality;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<String> images;

}
