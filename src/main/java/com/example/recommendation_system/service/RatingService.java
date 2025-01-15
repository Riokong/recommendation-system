package com.example.recommendation.service;

import com.example.recommendation.model.Rating;
import com.example.recommendation.model.User;
import com.example.recommendation.model.Item;
import com.example.recommendation.model.dto.RatingDTO;
import com.example.recommendation.repository.RatingRepository;
import com.example.recommendation.repository.UserRepository;
import com.example.recommendation.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public Rating createRating(RatingDTO ratingDTO) {
        User user = userRepository.findById(ratingDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Item item = itemRepository.findById(ratingDTO.getItemId())
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));

        // Check if rating already exists
        ratingRepository.findByUserIdAndItemId(user.getId(), item.getId())
                .ifPresent(r -> {
                    throw new IllegalArgumentException("Rating already exists");
                });

        Rating rating = new Rating();
        rating.setUser(user);
        rating.setItem(item);
        rating.setScore(ratingDTO.getScore());
        rating.setReview(ratingDTO.getReview());

        return ratingRepository.save(rating);
    }

    public List<Rating> getUserRatings(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found");
        }
        return ratingRepository.findByUserId(userId);
    }

    public List<Rating> getItemRatings(Long itemId) {
        if (!itemRepository.existsById(itemId)) {
            throw new EntityNotFoundException("Item not found");
        }
        return ratingRepository.findByItemId(itemId);
    }

    public Double getItemAverageRating(Long itemId) {
        if (!itemRepository.existsById(itemId)) {
            throw new EntityNotFoundException("Item not found");
        }
        return ratingRepository.getAverageRatingForItem(itemId);
    }

    public Rating updateRating(Long id, RatingDTO ratingDTO) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rating not found"));

        rating.setScore(ratingDTO.getScore());
        rating.setReview(ratingDTO.getReview());

        return ratingRepository.save(rating);
    }

    public void deleteRating(Long id) {
        if (!ratingRepository.existsById(id)) {
            throw new EntityNotFoundException("Rating not found");
        }
        ratingRepository.deleteById(id);
    }
}