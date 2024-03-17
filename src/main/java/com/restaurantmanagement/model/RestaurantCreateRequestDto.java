package com.restaurantmanagement.model;


import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantCreateRequestDto {

    @NotNull(message = "Name alanı zorunludur.")
    private String name;

    @NotNull(message = "Latitude alanı zorunludur.")
    private Double latitude;

    @NotNull(message = "Longitude alanı zorunludur.")
    private Double longitude;

    private Double rating;


}
