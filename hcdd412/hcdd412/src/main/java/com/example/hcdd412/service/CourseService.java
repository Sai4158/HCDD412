package com.example.hcdd412.service;

import com.example.hcdd412.model.Course;
import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> getAllCourses();
    void saveCourse(Course course);
    Optional<Course> getCourseById(long id);
    void deleteCourseById(long id);
}