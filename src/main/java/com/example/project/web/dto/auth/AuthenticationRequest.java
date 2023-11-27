package com.example.project.web.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @NotNull(message = "Email must not be null.")
    private String email;

    @NotNull(message = "Password must not be null.")
            @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
}
