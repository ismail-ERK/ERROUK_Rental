package com.example.location_voitures.requests.images;

import com.example.location_voitures.entities.VoitureEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImagesResuest {

    private String type;
    private String url;
    private VoitureEntity voiture;
}
