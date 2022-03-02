package com.example.location_voitures.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue
    long id;
    @Column(nullable = false)
    String nom;
    @Column(nullable = false)
    String image;
    @OneToMany(targetEntity = VoitureEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    List<VoitureEntity> voitures;

}
