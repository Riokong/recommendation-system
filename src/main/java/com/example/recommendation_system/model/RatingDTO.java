package com.example.recommendation.model.dto;

import lombok.Data;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class RatingDTO {
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Item ID is required")
    private Long itemId;

    @NotNull(message = "Rating score is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must not exceed 5")
    private Integer score;

    private String review;
}