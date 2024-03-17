package com.restaurantmanagement.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class UserDto {

    @NotNull(message = "Name alanı zorunludur.")
    private String name;

    @NotNull(message = "Surname alanı zorunludur.")
    private String surname;

    @NotNull(message = "Latitude alanı zorunludur.")
    private double latitude;

    @NotNull(message = "Longitude alanı zorunludur.")
    private double longitude;

}
