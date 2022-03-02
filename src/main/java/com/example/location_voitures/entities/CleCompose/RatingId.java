package com.example.location_voitures.entities.CleCompose;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Embeddable
public class RatingId implements Serializable {
    @Column(name = "user_id")
    long userId;
    @Column(name = "voiture_id")
    long voitureId;

}
