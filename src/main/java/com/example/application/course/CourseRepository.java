package com.example.application.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository <Course, Long>{
    @Query("SELECT c from Course c WHERE c.name = ?1")
    Optional<Course> findCourseByName(String name);

}
