package com.example.application.student;

import com.example.application.course.Course;
import com.example.application.course.CourseRepository;
import com.example.application.grade.Grade;
import com.example.application.grade.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student snig = new Student(
                    "Jan",
                    "Snieg",
                    LocalDate.of(1283, FEBRUARY, 18)
            );
            //Grade grade1 = new Grade(2.0);
           // snig.setGrades((Set<Grade>) grade1);

            Student rzemioslolubny = new Student(
                    "Heniek",
                    "Rzemioslolubny",
                    LocalDate.of(1937, MARCH, 15)
            );

            repository.saveAll(
                    List.of(snig, rzemioslolubny)
            );
        };
    }
    @Bean
    CommandLineRunner commandLineRunner2(CourseRepository courseRepository){
        return args -> {
            Course polski = new Course(
                    "Polski",
                    10
            );
             courseRepository.saveAll(
                    List.of(polski)
            );
        };
    }
    @Bean
    CommandLineRunner commandLineRunner3(GradeRepository gradeRepository){
        return args -> {
            Grade jedynka = new Grade(
                    1.0
            );
            Grade dwojka= new Grade(
                    2.0
            );
            Grade trojka = new Grade(
                    3.0
            );
            Grade czworka = new Grade(
                    4.0
            );
            Grade piatka= new Grade(
                    5.0
            );
            gradeRepository.saveAll(
                    List.of(jedynka,dwojka,trojka,czworka,piatka)
            );
        };
    }
}
