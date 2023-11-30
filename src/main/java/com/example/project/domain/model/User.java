package com.example.project.domain.model;


import com.example.project.domain.role.Role;
import com.example.project.web.token.Token;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;//username
    private String password;
    private String passwordConfirmation;
    @Column(name = "image")
    @CollectionTable(name = "students_Simages")
    @ElementCollection
    private List<String> images;

    @Enumerated(EnumType.STRING)
    private Role role;

    public String department;
    public String program;
    public String yearOfEnrollment;
    public String yearOfSubmission;
    public String dateOfBirth;

    @Column(unique = true)
    public String idNo;
    @Column(length = 1000)
    public String permanentAddress;
    @Column(length = 1000)
    public String maillingAddress;
    @Column(unique = true)
    public String phone;
    public String nationality;

    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles")
    @Enumerated(value = EnumType.STRING)
    private Set<Role> roles;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @OneToMany(mappedBy = "user")
    @CollectionTable(name = "users_token")
    private List<Token> token;

    @OneToMany
    @CollectionTable(name = "users_professors")
    private List<Professor> professor;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
