package com.example.hcdd412.controller;

import com.example.hcdd412.model.Student;
import com.example.hcdd412.service.CourseService; // We need this now
import com.example.hcdd412.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    // We also need the CourseService here to list courses on the update page
    @Autowired
    private CourseService courseService;

    // display list of students
    @GetMapping("/studentList")
    public String viewStudentPage(Model model) {
        model.addAttribute("listStudents", studentService.getAllStudents());
        return "student_list";
    }

    @GetMapping("/showNewStudentForm")
    public String showNewStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "new_student";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/studentList";
    }

    // --- THIS IS THE NEW PART TO ADD FOR UPDATING ---
    @GetMapping("/showStudFormForUpdate/{id}")
    public String showStudFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        // get student from the service
        Student student = studentService.getStudentById(id);

        // set student as a model attribute to pre-populate the form
        model.addAttribute("student", student);
        // Also add all courses to the model so we can choose them
        model.addAttribute("allCourses", courseService.getAllCourses());

        return "update_student";
    }
    // --- END OF NEW PART ---

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable(value = "id") long id) {
        this.studentService.deleteStudentById(id);
        return "redirect:/studentList";
    }
}