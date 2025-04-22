package com.example.rating_service.repository;

import com.example.rating_service.model.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingRepository extends MongoRepository<Rating, String> {
}
