package com.example.application.grade;

import com.example.application.course.Course;
import com.example.application.exception.BadRequestException;
import com.example.application.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {
    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }
    public void addNewGrade(Grade grade){
        Optional<Grade> gradeOptional = gradeRepository.findById(grade.getId());
        if(gradeOptional.isPresent()){
            throw new BadRequestException("This grade already exist.");
        }
        gradeRepository.save(grade);
    }

    public List<Grade> getGrades() {
        return gradeRepository.findAll();
    }
//    public void giveMark(Course course, Student student, Grade grade){
//        grade.setCourse(course);
//        grade.setStudent(student);
//        gradeRepository.save(grade);
//    }
}
