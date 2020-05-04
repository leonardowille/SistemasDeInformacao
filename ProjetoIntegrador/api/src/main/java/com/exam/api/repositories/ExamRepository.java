package com.exam.api.repositories;

import com.exam.api.models.Exam;
import com.exam.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findAllByUser(User user);
}
