package com.example.project.repository;

import com.example.project.domain.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    @Query("SELECT f FROM Faculty f WHERE f.facultyName = :facultyName")
    Optional<Faculty> findByFacultyName(String facultyName);
}
