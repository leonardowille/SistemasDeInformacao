package com.exam.api.controllers;

import com.exam.api.models.Exam;
import com.exam.api.repositories.ExamRepository;
import com.exam.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/exams")
public class ExamController {

    @Autowired
    ExamRepository examRepository;
    @Autowired
    private UserService userService;

    @GetMapping(value = "{id}")
    public Exam getExamById(@PathVariable(value = "id") long id) {
        return examRepository.findById(id).orElse(null);
    }

    @GetMapping()
    public List<Exam> getExams() {
        return examRepository.findAllByUser(userService.getCurrentUser());
    }

    @PostMapping()
    @PutMapping()
    public Exam saveExam(@RequestBody Exam exam) {
        exam.setUser(userService.getCurrentUser());
        return examRepository.save(exam);
    }

    @DeleteMapping(value = "{id}")
    public void deleteExamById(@PathVariable(value = "id") long id) {
        examRepository.deleteById(id);
    }
}
