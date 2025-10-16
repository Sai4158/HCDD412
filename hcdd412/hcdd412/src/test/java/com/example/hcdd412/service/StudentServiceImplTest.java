package com.example.hcdd412.service;

import com.example.hcdd412.model.Student;
import com.example.hcdd412.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class StudentServiceImplTest {

    @Autowired
    private StudentRepository studentRepository;

    // This method runs before each test to set up some data
    @BeforeEach
    void setUp() {
        Student student = new Student();
        student.setStudName("Sai Rangineeni");
        student.setStudEmail("sai@example.com");
        studentRepository.save(student);
    }

    @Test
    void testGetAllStudents() {
        // Find all students in the database
        List<Student> students = studentRepository.findAll();
        // Check that we found exactly 1 student
        assertEquals(1, students.size());
    }

    @Test
    void testFindOne() {
        // Try to find the first student (ID should be 1)
        Optional<Student> studentOptional = studentRepository.findById(1L);
        // Make sure the student was found
        assertTrue(studentOptional.isPresent());
        // Check if the student's name is correct
        assertEquals("Sai Rangineeni", studentOptional.get().getStudName());
    }
}