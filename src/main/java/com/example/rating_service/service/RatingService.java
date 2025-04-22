package com.example.rating_service.service;

import com.example.rating_service.dto.RatingDTO;
import com.example.rating_service.model.Rating;

import java.util.List;

public interface RatingService {
    Rating createRating(String movieId, RatingDTO ratingDTO);
    Rating updateRating(String ratingId, RatingDTO ratingDTO);
    void deleteRating(String ratingId);
    Rating getRating(String ratingId);
    List<Rating> getRatings();
}
