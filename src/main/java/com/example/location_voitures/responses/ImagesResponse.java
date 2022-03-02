package com.example.location_voitures.responses;

import com.example.location_voitures.entities.VoitureEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImagesResponse {
    private long id;
    private String type;
    private String url;
    private VoitureEntity voiture;
}
