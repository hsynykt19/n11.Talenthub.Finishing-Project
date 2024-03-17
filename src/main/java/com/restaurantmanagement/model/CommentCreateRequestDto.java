package com.restaurantmanagement.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CommentCreateRequestDto {

    @NotNull(message = "Text alanı zorunludur.")
    private String text;

    private int score;
    @NotNull(message = "Score alanı zorunludur.")

    @NotNull(message = "RestaurantName alanı zorunludur.")
    private String restaurantName;

    @NotNull(message = "UserName alanı zorunludur.")
    private String userName;
}
