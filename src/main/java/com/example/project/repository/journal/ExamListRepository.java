package com.example.project.repository.journal;

import com.example.project.model.journal.ExamList;
import org.springframework.data.jpa.repository.JpaRepository;

//@Api(tags = {"exams"})
public interface ExamListRepository extends JpaRepository<ExamList, Long> {

}
