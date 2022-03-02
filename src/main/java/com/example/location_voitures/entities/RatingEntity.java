package com.example.location_voitures.entities;

import com.example.location_voitures.entities.CleCompose.RatingId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "rating")
public class RatingEntity implements Serializable {
    @EmbeddedId
    RatingId ratingId;
    @ManyToOne()
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    UserEntity user;
    @ManyToOne()
    @MapsId("voitureId")
    @JoinColumn(name = "voiture_id")
    VoitureEntity voiture;
    @Column()
    int value;
}
