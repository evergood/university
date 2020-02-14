package com.foxminded.university.menu;

import com.foxminded.university.service.major.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all_students")
    public String showAllStudents(@RequestParam(name = "page", defaultValue = "1")
                                          String name, Model model) {
        model.addAttribute("students", studentService.getAllStudents(Integer.parseInt(name)));
        return "allStudents";
    }
}
