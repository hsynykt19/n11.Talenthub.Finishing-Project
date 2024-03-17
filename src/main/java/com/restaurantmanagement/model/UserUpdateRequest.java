package com.restaurantmanagement.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    @NotNull(message = "Name alanı zorunludur.")
    private String name;

    @NotNull(message = "Surname alanı zorunludur.")
    private String surname;

    @NotNull(message = "Latitude alanı zorunludur.")
    private Double latitude;

    @NotNull(message = "Longitude alanı zorunludur.")
    private Double longitude;

}
