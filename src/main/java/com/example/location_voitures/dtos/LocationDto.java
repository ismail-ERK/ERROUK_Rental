package com.example.location_voitures.dtos;

import com.example.location_voitures.entities.CleCompose.LigneLocationKey;
import com.example.location_voitures.entities.UserEntity;
import com.example.location_voitures.entities.VoitureEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocationDto {
    long userId;
    long voitureId;
    Date date_debut;
    Date date_fin;
}
