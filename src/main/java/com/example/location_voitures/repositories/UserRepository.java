package com.example.location_voitures.repositories;

import com.example.location_voitures.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    List<UserEntity> findAll();
    UserEntity findById(long id);
    UserEntity findByEmail(String email);
}
