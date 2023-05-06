package com.example.application.course;

import com.example.application.grade.Grade;
import com.example.application.rating.Rating;
import com.example.application.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long id;
    private String name;
    private Integer groupSize;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_students",
            joinColumns = @JoinColumn(name = "courseId"),
            inverseJoinColumns = @JoinColumn(name = "studentId")
    )
    private Set<Student> students= new HashSet<>();

//    @JsonIgnore
//    @OneToMany(mappedBy = "course")
//    private Set<Grade> grades = new HashSet<>();
    @JsonIgnore
    @OneToOne(mappedBy = "course")
    private Rating rating;


    public Course() {
    }

    public Course(Long id, String name, Integer groupSize) {
        this.id = id;
        this.name = name;
        this.groupSize = groupSize;
    }

    public Course(String name, Integer groupSize) {
        this.name = name;
        this.groupSize = groupSize;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public boolean studentExist(Student student){
        boolean exist = false;
        for(Student searchstudent: students){
            if(student.getId() == searchstudent.getId()) exist = true;
        }
        return exist;
    }

//    public Set<Grade> getGrades() {
//        return grades;
//    }
//
//    public void setGrades(Set<Grade> grades) {
//        this.grades = grades;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(Integer groupSize) {
        this.groupSize = groupSize;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", groupSize=" + groupSize +
                '}';
    }

    public void enrollStudent(Student student) {
        students.add(student);
    }

    public Double percentage(){
        double listSize = students.size();
        return listSize*100/this.groupSize;
    }

//    public void giveMark(Grade grade) {grades.add(grade);
//    }
}

