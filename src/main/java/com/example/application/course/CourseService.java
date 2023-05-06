package com.example.application.course;

import com.example.application.exception.ApiRequestException;
import com.example.application.exception.BadRequestException;
import com.example.application.grade.Grade;
import com.example.application.grade.GradeRepository;
import com.example.application.student.Student;
import com.example.application.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseService {
    private  final CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GradeRepository gradeRepository;


    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public List<Course> getCourses() { return courseRepository.findAll();
    }

    public Course getCourseByID(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    public void addNewCourse(Course course) {
        Optional<Course> courseOptional = courseRepository.findCourseByName(course.getName());
        if(courseOptional.isPresent()){
            throw new BadRequestException("There is course with that name");
        }
        courseRepository.save(course);
    }

    public void deleteCourse(Long courseId) {
        boolean exist = courseRepository.existsById(courseId);
        if (!exist){
            throw new ApiRequestException(
                    "Course with id "+ courseId + " does not exists.");
        }
        courseRepository.deleteById(courseId);
    }

    public Course saveStudentToCourse(Long courseId, Long studentId) {
        boolean existCourse = courseRepository.existsById(courseId);
        if(!existCourse){
            throw new ApiRequestException(
                    "course with id "+courseId+" does not exists");
        }
        Course course = courseRepository.getReferenceById(courseId);
        boolean exist = studentRepository.existsById(studentId);
        if(!exist){
            throw new ApiRequestException(
                    "student with id "+studentId+" does not exists");
        }
        Student student = studentRepository.getReferenceById(studentId);
        if(course.studentExist(student)){
            throw new BadRequestException("There is student with that name already save to this course");
        }
        course.enrollStudent(student);
        return courseRepository.save(course);
    }

//    public Course addGrade(Long courseId, Long studentId, Long gradeId) {
//        Course course = courseRepository.getReferenceById(courseId);
//        Student student = studentRepository.getReferenceById(studentId);
//        Grade grade = gradeRepository.getReferenceById(gradeId);
//        course.giveMark(grade);
//        student.getMark(grade);
//        studentRepository.save(student);
//        return courseRepository.save(course);
//    }

    public Set<Student> getStudentList(Long courseId) {
        boolean exist = courseRepository.existsById(courseId);
        if (!exist){
            throw new ApiRequestException(
                    "Course with id "+ courseId + " does not exists.");
        }
        Course course = courseRepository.getReferenceById(courseId);
        Set<Student> students = course.getStudents();
        return students;
    }

    public Double getPercentage(Long courseId) {
        boolean exist = courseRepository.existsById(courseId);
        if (!exist){
            throw new ApiRequestException(
                    "Course with id "+ courseId + " does not exists.");
        }
        Course course = courseRepository.getReferenceById(courseId);
        return course.percentage();
    }
}
