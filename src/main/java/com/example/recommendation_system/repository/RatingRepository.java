package com.example.recommendation.repository;

import com.example.recommendation.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByUserId(Long userId);
    List<Rating> findByItemId(Long itemId);
    Optional<Rating> findByUserIdAndItemId(Long userId, Long itemId);

    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.item.id = :itemId")
    Double getAverageRatingForItem(@Param("itemId") Long itemId);
}