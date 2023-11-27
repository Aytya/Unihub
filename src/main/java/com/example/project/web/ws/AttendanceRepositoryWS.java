package com.example.project.web.ws;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepositoryWS extends JpaRepository<AttendanceStatus, Integer> {

}
