package com.example.location_voitures.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgencesResponse {
    private long id;
    private String name;
    private String adresse;
    private String ville;
    private String image;
    private String image360;
    private List<VoitureResponse> voitures;
}
