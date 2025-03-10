package com.college.controller;

import com.college.Enrollment;
import com.college.service.EnrollmentService;
import com.college.service.StudentService;
import com.college.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/enrollment")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    private final StudentService studentService;
    private final CourseService courseService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService,
                              StudentService studentService,
                              CourseService courseService) {
        this.enrollmentService = enrollmentService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("/enroll")
    public String showEnrollmentForm(Model model) {
        model.addAttribute("enrollment", new Enrollment());
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("courses", courseService.findAll());
        return "enrollment/enroll-form";
    }

    @PostMapping("/enroll")
    public String enroll(@RequestParam(value = "student", required = false) Long studentId,
                        @RequestParam(value = "course", required = false) Long courseId,
                        Model model) {
        if (studentId == null || courseId == null) {
            model.addAttribute("enrollment", new Enrollment());
            model.addAttribute("students", studentService.findAll());
            model.addAttribute("courses", courseService.findAll());
            return "enrollment/enroll-form";
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(studentService.findById(studentId).orElse(null));
        enrollment.setCourse(courseService.findById(courseId).orElse(null));
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollmentService.enroll(enrollment);
        return "redirect:/schedule/list";
    }
}