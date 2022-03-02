package com.example.location_voitures.repositories;

import com.example.location_voitures.entities.AgencesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgencesRepository extends JpaRepository<AgencesEntity, Long> {
//    List<AgencesEntity> findAll();
    AgencesEntity findById(long id);
    @Query(value = "SELECT * FROM agences", nativeQuery = true)
    List<AgencesEntity> findAll();
}
