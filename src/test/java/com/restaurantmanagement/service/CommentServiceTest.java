package com.restaurantmanagement.service;

import com.restaurantmanagement.entity.Restaurant;
import com.restaurantmanagement.entity.UserComment;
import com.restaurantmanagement.entity.User;
import com.restaurantmanagement.model.CommentCreateRequestDto;
import com.restaurantmanagement.model.CommentUpdateRequest;
import com.restaurantmanagement.repository.CommentRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = {"test"})
public class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private UserService userService;
    @Mock
    private RestaurantService restaurantService;

    @Test
    public void whenSaveComment_shouldReturnComment() {

        CommentCreateRequestDto commentCreateRequestDto = new CommentCreateRequestDto();
        commentCreateRequestDto.setText("Excellent restaurant");
        commentCreateRequestDto.setScore(4);

        UserComment comment = new UserComment();
        comment.setText(commentCreateRequestDto.getText());
        comment.setScore(commentCreateRequestDto.getScore());
        User user = getUsers();
        Restaurant restaurant = getRestaurant();
        Mockito.when(commentRepository.save(any())).thenReturn(comment);
        Mockito.when(userService.getUser(any())).thenReturn(user);
        Mockito.when(restaurantService.getRestaurant(any())).thenReturn(restaurant);

        UserComment savedComment = commentService.create(commentCreateRequestDto);
        assertNotNull(savedComment);
        assertEquals(commentCreateRequestDto.getText(), comment.getText());

    }

    private Restaurant getRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setLatitude(1000);
        restaurant.setLongitude(10000);
        restaurant.setName("KFC");
        restaurant.setScore(2);
        return restaurant;
    }


    @Test
    public void testUpdateComment() {
        UserComment existingComment = new UserComment();
        existingComment.setId(1L);
        existingComment.setText("Great restaurant");
        existingComment.setScore(5);

        UserComment updatedComment = new UserComment();
        updatedComment.setId(1L);
        updatedComment.setText("Excellent restaurant");
        updatedComment.setScore(4);

        CommentUpdateRequest commentUpdateRequest = new CommentUpdateRequest();
        commentUpdateRequest.setText("Excellent restaurant");
        commentUpdateRequest.setScore(4);

        Mockito.when(commentRepository.findById(1L)).thenReturn(Optional.of(existingComment));
        Mockito.when(commentRepository.save(any(UserComment.class))).thenReturn(updatedComment);

        Optional<UserComment> result = commentService.update(1L, commentUpdateRequest);

        assertNotNull(result);
        UserComment comment = result.get();
        assertEquals("Excellent restaurant", comment.getText());
    }

    @Test
    public void testDeleteUser() {
        UserComment comment = new UserComment();
        comment.setId(1L);
        comment.setText("Great restaurant");
        comment.setScore(4);


        Mockito.when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));

        commentService.deleteComment(comment.getId());

        verify(commentRepository).deleteById(comment.getId());
    }

    @Test
    public void whenGivenId_shouldDeleteComment_ifFound() {
        UserComment comment = new UserComment();
        comment.setId(1L);
        comment.setText("Great restaurant");
        comment.setScore(4);
        User user = new User();
        user.setId(1L);
        user.setName("Hüseyin");
        user.setSurName("Yakut");
        user.setLatitude(65.6584);
        user.setLongitude(-36.0965);
        comment.setUser(user);

        Mockito.when(commentRepository.findById(any())).thenReturn(Optional.of(comment));

        commentService.deleteComment(comment.getId());

        verify(commentRepository).deleteById(comment.getId());
    }

    private User getUsers() {
        User createUser = new User();
        createUser.setId(1L);
        createUser.setName("İsmail");
        createUser.setSurName("Doğan");
        createUser.setLatitude(40.7128);
        createUser.setLongitude(-74.0060);
        return createUser;
    }

}
