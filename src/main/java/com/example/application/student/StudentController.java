package com.example.application.student;

import com.example.application.grade.Grade;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping("/csv")
    public void getStudentsCSV(HttpServletResponse response)throws IOException {
        studentService.getStudentsCSC(response);
    }

    @GetMapping(path = "{studentId}")
    public Student getStudentByID(@PathVariable("studentId")Long studentId){
        return studentService.getStudentByID(studentId);
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId")Long studentId){
        studentService.deleteStudent(studentId);
    }

    @GetMapping(path = "{studentId}/grade")
    public Set<Grade> getGradesList(
            @PathVariable("studentId") Long studentId){
        return studentService.getGradeList(studentId);
    }

    @PutMapping("/{studentId}/grade/{gradeId}")
     public Student addStudentsGrade(
             @PathVariable Long studentId,
             @PathVariable Long gradeId
     ){
        return studentService.addGrade( studentId, gradeId);
     }

}
