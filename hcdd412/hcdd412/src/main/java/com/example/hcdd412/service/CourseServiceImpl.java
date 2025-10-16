package com.example.hcdd412.service;

import com.example.hcdd412.model.Course;
import com.example.hcdd412.model.Student;
import com.example.hcdd412.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void saveCourse(Course course) {
        this.courseRepository.save(course);
    }

    @Override
    public Optional<Course> getCourseById(long id) {
        return courseRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteCourseById(long id) {

        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));

        for (Student student : course.getStudents()) {
            student.getCourses().remove(course);
        }

        this.courseRepository.delete(course);
    }
}