package com.example.application.student;

import com.example.application.exception.ApiRequestException;
import com.example.application.exception.BadRequestException;
import com.example.application.grade.Grade;
import com.example.application.grade.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Autowired
    GradeRepository gradeRepository;

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByLastName(student.getLastName());
        if(studentOptional.isPresent()){
            throw new BadRequestException("There is student with that last name");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if(!exist){
            throw new ApiRequestException(
                    "student with id "+studentId+" does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    public Student getStudentByID(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if(!exist){
            throw new ApiRequestException(
                    "student with id "+studentId+" does not exists");
        }
        return studentRepository.findById(studentId).orElse(null);
    }

    public Student addGrade(Long studentId, Long gradeId) {
        boolean exist = studentRepository.existsById(studentId);
        if(!exist){
            throw new ApiRequestException(
                    "student with id "+studentId+" does not exists");
        }
        Student student = studentRepository.getReferenceById(studentId);
        boolean existGrade = gradeRepository.existsById(gradeId);
        if(!existGrade){
            throw new ApiRequestException(
                    "There is no grade to give.");
        }
        Grade grade = gradeRepository.getReferenceById(gradeId);
        student.addMark(grade);
        return studentRepository.save(student);
    }

    public Set<Grade> getGradeList(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if(!exist){
            throw new ApiRequestException(
                    "student with id "+studentId+" does not exists");
        }
        Student student = studentRepository.getReferenceById(studentId);
        Set<Grade> grades = student.getGrades();
        return grades;
    }
}
