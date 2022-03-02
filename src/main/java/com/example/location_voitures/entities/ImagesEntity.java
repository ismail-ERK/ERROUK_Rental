package com.example.location_voitures.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name = "images")
public class ImagesEntity implements Serializable {
    @Id()
    @GeneratedValue()
    private long id;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String url;
    @ManyToOne(cascade = CascadeType.ALL)
    private VoitureEntity voiture;
}
