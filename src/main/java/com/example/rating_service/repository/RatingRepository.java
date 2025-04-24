package com.example.rating_service.repository;

import com.example.rating_service.model.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, String> {
    List<Rating> findBymovieId(String movieId);
}
