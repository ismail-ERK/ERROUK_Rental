package com.example.location_voitures.repositories;

import com.example.location_voitures.entities.ImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagesRepository extends JpaRepository<ImagesEntity, Long> {
    List<ImagesEntity> findAll();
    List<ImagesEntity> findByVoiture_Id(long id);
    ImagesEntity findById(long id);

}
