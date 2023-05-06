package com.example.application.grade;

import com.example.application.course.Course;
import com.example.application.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double value;
    @JsonIgnore
    @ManyToMany(mappedBy = "grades")
    private Set<Student> students = new HashSet<>();


//    @ManyToOne
//    @JoinColumn(name = "studentId")
//    private Student student;

//    @ManyToOne
//    @JoinColumn(name = "courseId")
//    private Course course;

    public Grade() {
    }

    public Grade(Long id, Double value) {
        this.id = id;
        this.value = value;
    }

    public Grade(Double value) {
        this.value = value;
    }

//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }
//
//    public Course getCourse() {
//        return course;
//    }
//
//    public void setCourse(Course course) {
//        this.course = course;
//    }




    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        if(value < 1 || value > 5.0){
            throw new IllegalArgumentException("Rating should be between 1 and 5.");
        }
        this.value = value;
    }
}
