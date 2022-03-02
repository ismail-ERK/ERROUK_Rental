package com.example.location_voitures.entities;

import com.example.location_voitures.entities.CleCompose.LigneLocationKey;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "locations")
@ToString
public class LocationEntity implements Serializable {

    @EmbeddedId
    LigneLocationKey locationId;
    @ManyToOne()
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    UserEntity user;
    @ManyToOne()
    @MapsId("voitureId")
    @JoinColumn(name = "voiture_id")
    VoitureEntity voiture;
    @Column()
    Date date_debut;
    @Column()
    Date date_fin;
}
