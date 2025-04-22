package com.example.rating_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(
        "Rating")
@Data
public class Rating {
    @Id
    private String id;
    private int ratingStar;
    private String ratingContent;
    private String movieId;
}
