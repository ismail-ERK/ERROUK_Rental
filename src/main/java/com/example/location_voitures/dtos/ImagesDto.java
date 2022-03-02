package com.example.location_voitures.dtos;

import com.example.location_voitures.entities.VoitureEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImagesDto {
    private long id;
    private String type;
    private String url;
    private VoitureEntity voiture;
}
