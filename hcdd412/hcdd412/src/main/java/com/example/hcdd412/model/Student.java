package com.example.hcdd412.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    @Column(name = "stud_name")
    private String studName;

    @Column(name = "stud_email")
    private String studEmail;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "student_course",
            joinColumns = { @JoinColumn(name = "sid") },
            inverseJoinColumns = { @JoinColumn(name = "id") }
    )
    private Set<Course> courses = new HashSet<>();

    // Getters and Setters
    public Long getSid() { return sid; }
    public void setSid(Long sid) { this.sid = sid; }
    public String getStudName() { return studName; }
    public void setStudName(String studName) { this.studName = studName; }
    public String getStudEmail() { return studEmail; }
    public void setStudEmail(String studEmail) { this.studEmail = studEmail; }
    public Set<Course> getCourses() { return courses; }
    public void setCourses(Set<Course> courses) { this.courses = courses; }
}