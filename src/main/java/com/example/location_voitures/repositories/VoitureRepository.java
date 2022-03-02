package com.example.location_voitures.repositories;

import com.example.location_voitures.entities.VoitureEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoitureRepository extends JpaRepository<VoitureEntity, Long> {
    List<VoitureEntity> findAll();

    List<VoitureEntity> findByAgence_Id(long id);

    VoitureEntity findById(long id);
}