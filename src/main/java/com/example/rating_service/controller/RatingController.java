package com.example.rating_service.controller;

import com.example.rating_service.dto.RatingDTO;
import com.example.rating_service.model.Rating;
import com.example.rating_service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;


    @PreAuthorize("hasRole('USER')")
    @PostMapping()
    public ResponseEntity<Rating> createRating(@RequestParam String movieId, @RequestBody RatingDTO ratingDTO){
        Rating rating = ratingService.createRating(movieId, ratingDTO);
        return ResponseEntity.ok(rating);
    }

    @PutMapping("/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable String ratingId, @RequestBody RatingDTO ratingDTO){
        Rating rating = ratingService.updateRating(ratingId, ratingDTO);
        return ResponseEntity.ok(rating);
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRating(@PathVariable String ratingId){
        Rating rating = ratingService.getRating(ratingId);
        return ResponseEntity.ok(rating);
    }

    @DeleteMapping("/{ratingId}")
    public ResponseEntity<Rating> deleteRating(@PathVariable String ratingId){
        Rating rating = ratingService.getRating(ratingId);
        return ResponseEntity.ok(null);
    }

    @GetMapping()
    public ResponseEntity<List<Rating>> getRatings(){
        List<Rating> ratings = ratingService.getRatings();
        return ResponseEntity.ok(ratings);
    }
}
