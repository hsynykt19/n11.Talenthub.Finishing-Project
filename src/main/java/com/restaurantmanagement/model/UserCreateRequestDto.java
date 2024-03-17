package com.restaurantmanagement.model;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserCreateRequestDto {



    @NotNull(message = "Name alanı zorunludur.")
    private String name;

    @NotNull(message = "Surname alanı zorunludur.")
    private String surName;

    @NotNull(message = "Latitude alanı zorunludur.")
    private Double latitude;

    @NotNull(message = "Longitude alanı zorunludur.")
    private Double longitude;
}
