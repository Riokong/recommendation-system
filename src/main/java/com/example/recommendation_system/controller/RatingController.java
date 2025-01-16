package com.example.recommendation.controller;

import com.example.recommendation.model.Rating;
import com.example.recommendation.model.dto.RatingDTO;
import com.example.recommendation.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> createRating(@Valid @RequestBody RatingDTO ratingDTO) {
        return ResponseEntity.ok(ratingService.createRating(ratingDTO));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getUserRatings(@PathVariable Long userId) {
        return ResponseEntity.ok(ratingService.getUserRatings(userId));
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<List<Rating>> getItemRatings(@PathVariable Long itemId) {
        return ResponseEntity.ok(ratingService.getItemRatings(itemId));
    }

    @GetMapping("/item/{itemId}/average")
    public ResponseEntity<Double> getItemAverageRating(@PathVariable Long itemId) {
        return ResponseEntity.ok(ratingService.getItemAverageRating(itemId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable Long id, @Valid @RequestBody RatingDTO ratingDTO) {
        return ResponseEntity.ok(ratingService.updateRating(id, ratingDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
        return ResponseEntity.ok().build();
    }
}