package com.example.location_voitures.requests.agences;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgencesRequest {
    private String name;
    private String adresse;
    private String ville;
    private String image;
    private String image360;
}
