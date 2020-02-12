package com.foxminded.university.controller;

import com.foxminded.university.service.major.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.IntStream;

@Controller
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String showAllStudents(@RequestParam(name = "page", defaultValue = "1")
                                          String page, Model model) {
        model.addAttribute("pages", IntStream.rangeClosed(1, studentService.getMaxPage()).toArray());
        model.addAttribute("students", studentService.getAllStudents(page));
        return "allStudents";
    }
}
