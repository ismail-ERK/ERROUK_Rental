package com.example.location_voitures.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarsCriteria {
    Long agence;
    String couleur;
    String marque;
    String modele;
    Integer nbrPortes;
    Integer nbrPlaces;
    Double prixMin;
    Double prixMax;
    String transmission;
}
