package com.example.project.domain.model;


import com.example.project.domain.role.Role;
import com.example.project.web.token.Token;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

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

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_image", joinColumns = @JoinColumn(name = "_user_id"))
//    @Column(name = "fileName")
//    private List<String> images;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name="attendance_id")
    private Attendance attendance;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Task> tasks;

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
