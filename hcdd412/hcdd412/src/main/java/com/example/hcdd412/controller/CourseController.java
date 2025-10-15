package com.example.hcdd412.controller;

import com.example.hcdd412.model.Course;
import com.example.hcdd412.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    // display list of courses
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listCourses", courseService.getAllCourses());
        return "index";
    }

    @GetMapping("/showNewCourseForm")
    public String showNewCourseForm(Model model) {
        // create model attribute to bind form data
        Course course = new Course();
        model.addAttribute("course", course);
        return "new_course";
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course) {
        // save course to database
        courseService.saveCourse(course);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        // get course from the service
        Optional<Course> courseOptional = courseService.getCourseById(id);
        Course course = courseOptional.orElseThrow(() -> new RuntimeException("Course not found"));
        // set course as a model attribute to pre-populate the form
        model.addAttribute("course", course);
        return "update_course";
    }

    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable(value = "id") long id) {
        // call delete course method
        this.courseService.deleteCourseById(id);
        return "redirect:/";
    }
}