package com.momo.fitnessTracker.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsDto {

    private String firstName;

    private String lastName;

    private int age;

    private String email;
}
