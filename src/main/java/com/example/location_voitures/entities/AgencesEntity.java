package com.example.location_voitures.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
    @Table(name = "agences")
public class AgencesEntity implements Serializable {
    @Id()
    @GeneratedValue()
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String adresse;
    @Column(nullable = false)
    private String ville;
    @Column(nullable = false)
    private String image;
    @Column()
    private String image360;
    @OneToMany(targetEntity = VoitureEntity.class,cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private List<VoitureEntity> voitures;
}
