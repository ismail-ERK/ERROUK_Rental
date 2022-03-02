package com.example.location_voitures.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "customers")
public class CustomerEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false,unique = true)
    String email;
    @Column(nullable = false,unique = true)
    String customerID;
}
