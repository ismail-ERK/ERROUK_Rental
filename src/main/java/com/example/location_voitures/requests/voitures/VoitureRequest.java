package com.example.location_voitures.requests.voitures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoitureRequest {
    private String numeroImmatriculation;
    private String couleur;
    private String marque;
    private String modele;
    private String transmission;
    private boolean disponibilite;
    private int nbrPortes;
    private int nbrPlaces;
    private double prixJour;
}
