package com.restaurantmanagement.model;

import com.restaurantmanagement.entity.User;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotNull;
@Data
@Builder
@AllArgsConstructor
public class CommentDto {


    private Long id;

    @NotNull(message = "Text alanı zorunludur")
    private String text;

    private int score;

    private User user;

}
