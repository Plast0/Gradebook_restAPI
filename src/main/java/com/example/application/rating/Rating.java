package com.example.application.rating;

import com.example.application.course.Course;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private Integer value;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "courseId", referencedColumnName = "id")
    private Course course;

    private LocalDate dateOfEvaluation;

    public Rating() {
    }

    public Rating(Long id, Integer value, Course course, LocalDate dateOfEvaluation) {
        this.id = id;
        this.value = value;
        this.course = course;
        this.dateOfEvaluation = dateOfEvaluation;
    }

    public Rating(Integer value, Course course, LocalDate dateOfEvaluation) {
        this.value = value;
        this.course = course;
        this.dateOfEvaluation = dateOfEvaluation;
    }

    public Rating(Integer value, LocalDate dateOfEvaluation) {
        this.value = value;
        this.dateOfEvaluation = dateOfEvaluation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        if(value < 0 || value > 5){
            throw new IllegalArgumentException("Rating should be between 0 and 5.");
        }
        this.value = value;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getDateOfEvaluation() {
        return dateOfEvaluation;
    }

    public void setDateOfEvaluation(LocalDate dateOfEvaluation) {
        this.dateOfEvaluation = dateOfEvaluation;
    }
}
