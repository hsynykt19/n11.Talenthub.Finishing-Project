package com.restaurantmanagement.service;

import com.restaurantmanagement.entity.Restaurant;
import com.restaurantmanagement.entity.UserComment;
import com.restaurantmanagement.entity.User;
import com.restaurantmanagement.exception.CommentNotFoundException;
import com.restaurantmanagement.model.CommentCreateRequestDto;
import com.restaurantmanagement.model.CommentUpdateRequest;
import com.restaurantmanagement.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final RestaurantService restaurantService;

    public UserComment create(CommentCreateRequestDto commentDto) {
        UserComment comment = new UserComment();
        comment.setScore(commentDto.getScore());
        comment.setText(commentDto.getText());
        Restaurant restaurant = restaurantService.getRestaurant(commentDto.getRestaurantName());
        User commentUser = userService.getUser(commentDto.getUserName());
        if (Objects.isNull(commentUser) || Objects.isNull(restaurant)) {
            throw new IllegalArgumentException("User ya da restaurant bilgisi bulunmamaktadır");

        }
        comment.setUser(commentUser);
        comment.setRestaurant(restaurant);
        if (commentDto.getScore() < 1 || commentDto.getScore() > 5) {
            throw new IllegalArgumentException("Score bilgisi 1 ile 5 arasında olmalıdır");

        }
        return commentRepository.save(comment);
    }

    public Optional<UserComment> update(Long id, CommentUpdateRequest commentUpdateRequest) {
        UserComment comment = commentRepository.findById(id).orElseThrow();
        comment.setScore(commentUpdateRequest.getScore());
        comment.setText(commentUpdateRequest.getText());
        return Optional.of(commentRepository.save(comment));
    }

    public void deleteComment(Long id) {
        commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
        commentRepository.deleteById(id);
    }


}
