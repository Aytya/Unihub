package com.example.project.repository;

import com.example.project.domain.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("select p from Professor p")
    public List<Professor> getAllTeacher();
}
