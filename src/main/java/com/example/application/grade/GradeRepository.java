package com.example.application.grade;

import com.example.application.course.Course;
import com.example.application.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    //void giveMark(Course course, Student student, Grade grade);

}

