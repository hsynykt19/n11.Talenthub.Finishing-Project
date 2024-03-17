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
public class CommentUpdateRequest {

    @NotNull(message = "Text alanı zorunludur.")
    private String text;

    private int score;
}
