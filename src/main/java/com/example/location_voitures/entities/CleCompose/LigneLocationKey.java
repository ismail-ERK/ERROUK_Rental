package com.example.location_voitures.entities.CleCompose;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class LigneLocationKey implements Serializable {

    @Column(name = "user_id")
    private long userId;
    @Column(name = "voiture_id")
    private long voitureId;
}
