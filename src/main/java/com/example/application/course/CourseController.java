package com.example.application.course;

import com.example.application.grade.Grade;
import com.example.application.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourses(){return courseService.getCourses();}
    @GetMapping(path = "{courseId}")
    public Course getCourseID(@PathVariable("courseId")Long courseId){
        return courseService.getCourseByID(courseId);
    }

    @GetMapping ("/{courseId}/students")
    public Set<Student> getStudentsList(
            @PathVariable("courseId") Long courseId ){
        return courseService.getStudentList(courseId);
    }
    @GetMapping("/{courseId}/fill")
    public Double getPercentage(@PathVariable("courseId") Long courseId){
        return courseService.getPercentage(courseId);
    }

    @PostMapping
    public void registerNewCourse(@RequestBody Course course){
        courseService.addNewCourse(course);
    }


//    public void addStudentsGrade(@RequestBody Grade grade,
//             @PathVariable Long courseId,
//             @PathVariable Long studentId
//     ){
//        courseService.addGrade(courseId, studentId, grade);
//     }
    @DeleteMapping(path = "{courseId}")
    public void deleteCourse(@PathVariable("courseId")Long courseId){
        courseService.deleteCourse(courseId);
    }
   @PutMapping("/{courseId}/student/{studentId}")
     Course enrollStudentToCourse(
            @PathVariable Long courseId,
            @PathVariable Long studentId
    ){
        return courseService.saveStudentToCourse(courseId, studentId);
     }
//     @PutMapping("/{courseId}/student/{studentId}/{gradeId}")
//     public Course addStudentsGrade(
//             @PathVariable Long courseId,
//             @PathVariable Long studentId,
//             @PathVariable Long gradeId
//     ){
//        return courseService.addGrade(courseId, studentId, gradeId);
//     }



}
