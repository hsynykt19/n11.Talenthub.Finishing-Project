package com.restaurantmanagement.model;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RestaurantDto {

    @NotNull(message = "Name alanı zorunludur.")
    private String name;

    @NotNull(message = "Latitude alanı zorunludur.")
    private double latitude;

    @NotNull(message = "Longitude alanı zorunludur.")
    private double longitude;

    private double rating;
}
