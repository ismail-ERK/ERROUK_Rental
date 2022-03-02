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
@Table(name = "payments")
public class PaymentEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column(nullable = false)
    double amount;
    @Column()
    String currency;

}
