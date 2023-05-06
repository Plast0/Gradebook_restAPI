package com.example.application.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/rating")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/{courseId}")
    public void addNewRating(@RequestBody Rating rating,
                             @PathVariable Long courseId){
        ratingService.addNewRating(rating, courseId);
    }
    @GetMapping
    public List<Rating> getRating(){
        return ratingService.getRatings();
    }
}
