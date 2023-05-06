package com.example.application.grade;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/grade")
public class GradeController {
    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }
    @GetMapping
    public List<Grade> getGrades(){return gradeService.getGrades();}

    @PostMapping
    public void addNewMark(@RequestBody Grade grade){
        gradeService.addNewGrade(grade);
    }
}
