package com.example.location_voitures.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgencesDto {
    private long id;
    private String name;
    private String adresse;
    private String ville;
    private String image;
    private String image360;
    private List<VoitureDto> voitures;
}
