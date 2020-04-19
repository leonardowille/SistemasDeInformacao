package com.exam.api.controllers;

import com.exam.api.models.Exam;
import com.exam.api.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/exam")
public class ExamController {

    @Autowired
    ExamRepository examRepository;

    @GetMapping(value = "{id}")
    public Exam getExamById(@PathVariable(value = "id") long id) {
        return examRepository.findById(id).orElse(null);
    }

    @PostMapping()
    @PutMapping()
    public Exam saveExam(@RequestBody Exam exam) {
        return examRepository.save(exam);
    }

    @DeleteMapping(value = "{id}")
    public void deleteExamById(@PathVariable(value = "id") long id) {
        examRepository.deleteById(id);
    }
}
