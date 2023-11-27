package com.example.project.repository.faculty;

import com.example.project.model.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("select p from Professor p")
    public List<Professor> getAllTeacher();
}
