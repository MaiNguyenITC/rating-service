package com.example.rating_service.service;

import com.example.rating_service.dto.RatingDTO;
import com.example.rating_service.exception.BadRequestException;
import com.example.rating_service.exception.NotFoundException;
import com.example.rating_service.model.Rating;
import com.example.rating_service.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public class RatingServiceImpl implements com.example.rating_service.service.RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    private final StreamBridge streamBridge;

    public RatingServiceImpl(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }
    @Override
    public Rating createRating(String movieId, RatingDTO ratingDTO) {
        Rating rating = new Rating();

        if(ratingDTO.getRatingStar() <= 0){
            throw new BadRequestException("Rating star must be > 0");
        }
        rating.setMovieId(movieId);
        rating.setRatingStar(ratingDTO.getRatingStar());
        rating.setRatingContent(ratingDTO.getRatingContent());
        ratingRepository.save(rating);
        streamBridge.send("createRating-out-0", movieId);
        return rating;
    }

    @Override
    public Rating updateRating(String ratingId, RatingDTO ratingDTO) {
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(
                () -> new NotFoundException("Rating is not found with id: " + ratingId)
        );
        if(ratingDTO.getRatingStar() <= 0){
            throw new BadRequestException("Rating star must be > 0");
        }
        rating.setRatingStar(ratingDTO.getRatingStar());
        rating.setRatingContent(ratingDTO.getRatingContent());
        return ratingRepository.save(rating);
    }

    @Override
    public void deleteRating(String ratingId) {
        ratingRepository.deleteById(ratingId);
    }

    @Override
    public Rating getRating(String ratingId) {
        return ratingRepository.findById(ratingId).orElseThrow(
                () -> new NotFoundException("Rating is not found with id: " + ratingId)
        );
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByMovieId(String movieId) {

        return ratingRepository.findBymovieId(movieId);
    }

    @Bean
    public Consumer<String> createMovie() {
        return movieString -> {
            System.out.println("📥 Nhận message từ RabbitMQ: " + movieString);
        };
    }

    @Bean
    public Consumer<String> getMovie() {
        return movieString -> {
            System.out.println("📥 Nhận message từ RabbitMQ - get: " + movieString);
        };
    }

}
