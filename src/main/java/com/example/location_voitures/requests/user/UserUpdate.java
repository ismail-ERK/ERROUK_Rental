package com.example.location_voitures.requests.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdate {
    private String firstName;
    private String lastName;
    private String email;
}
