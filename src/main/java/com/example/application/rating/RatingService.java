package com.example.application.rating;

import com.example.application.course.Course;
import com.example.application.course.CourseRepository;
import com.example.application.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Autowired
    CourseRepository courseRepository;

    public void addNewRating(Rating rating, Long id){
        Course course = courseRepository.findById(id).orElse(null);
        boolean exist = courseRepository.existsById(id);
        if (!exist){
            throw new ApiRequestException(
                    "Course with id "+ id + " does not exists.");
        }
        else {
            Rating ratingAdded = new Rating(rating.getValue(), course, rating.getDateOfEvaluation());
            ratingRepository.save(ratingAdded);
        }
    }


    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }
}
