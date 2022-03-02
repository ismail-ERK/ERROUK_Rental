package com.example.location_voitures.repositories;

import com.example.location_voitures.entities.CleCompose.LigneLocationKey;
import com.example.location_voitures.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<LocationEntity, LigneLocationKey> {
    List<LocationEntity> findAll();
    LocationEntity findByLocationId(LigneLocationKey id);
}
