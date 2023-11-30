package com.example.project.repository;

import com.example.project.domain.model.FinanceCabinet;
import com.example.project.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinanceCabinetRepository extends JpaRepository<FinanceCabinet, Long> {
    Optional<FinanceCabinet> findByUser(User user);
}
