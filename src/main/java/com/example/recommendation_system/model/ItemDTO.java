package com.example.recommendation.model.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class ItemDTO {
    @NotBlank(message = "Name is required")
    private String name;

    private String description;
    private String category;
}