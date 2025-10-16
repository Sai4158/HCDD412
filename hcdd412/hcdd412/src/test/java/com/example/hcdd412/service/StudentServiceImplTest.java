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

    @BeforeEach
    void setUp() {
        studentRepository.deleteAll();

        Student student = new Student();
        student.setStudName("Sai Rangineeni");
        student.setStudEmail("sai@example.com");
        studentRepository.save(student);
    }

    @Test
    void testGetAllStudents() {

        List<Student> students = studentRepository.findAll();

        assertEquals(1, students.size());
    }

    @Test
    void testFindOne() {
        List<Student> students = studentRepository.findAll();
        Long studentId = students.get(0).getSid();

        Optional<Student> studentOptional = studentRepository.findById(studentId);

        assertTrue(studentOptional.isPresent());

        assertEquals("Sai Rangineeni", studentOptional.get().getStudName());
    }
}