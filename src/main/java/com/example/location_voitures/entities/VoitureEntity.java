package com.example.location_voitures.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "voitures")
public class VoitureEntity {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String numeroImmatriculation;
    @Column(nullable = false)
    private String couleur;
    @Column(nullable = false)
    private String marque;
    @Column(nullable = false)
    private String modele;
    @Column(nullable = false)
    private String transmission;
    @Column(nullable = false)
    private boolean disponibilite;
    @Column(nullable = false)
    private int nbrPortes;
    @Column(nullable = false)
    private int nbrPlaces;
    @Column(nullable = false)
    private double prixJour;
    @ManyToOne(cascade = CascadeType.ALL)
    private AgencesEntity agence;
    @OneToMany(targetEntity = ImagesEntity.class,cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private List<ImagesEntity> images;
    @ManyToOne(cascade = CascadeType.ALL)
    private CategoryEntity category;
    @OneToMany(mappedBy = "voiture",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<LocationEntity> locations;
    @OneToMany(mappedBy = "voiture", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<RatingEntity> ratings;
}
