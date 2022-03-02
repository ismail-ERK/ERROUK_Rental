package com.example.location_voitures.repositories;

import com.example.location_voitures.entities.CleCompose.RatingId;
import com.example.location_voitures.entities.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, RatingId> {
    List<RatingEntity> findAll();
    RatingEntity findByRatingId(RatingId id);
}
