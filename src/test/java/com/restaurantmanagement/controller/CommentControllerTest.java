package com.restaurantmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantmanagement.entity.UserComment;
import com.restaurantmanagement.model.*;
import com.restaurantmanagement.service.CommentService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void whenCommentRequestIsNull() throws Exception {
        CommentCreateRequestDto commentCreateRequestDto = null;
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/N11/UserComments/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentCreateRequestDto)))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void when_UserName_IsNull_RequestCheck() throws Exception {
        CommentCreateRequestDto commentCreateRequestDto = getCreateRequestDto();
        commentCreateRequestDto.setUserName(null);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/N11/UserComments/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentCreateRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void when_RestaurantName_IsNull_RequestCheck() throws Exception {
        CommentCreateRequestDto commentCreateRequestDto = getCreateRequestDto();
        commentCreateRequestDto.setRestaurantName(null);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/N11/UserComments/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentCreateRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void when_Text_IsNull_RequestCheck() throws Exception {
        CommentCreateRequestDto commentCreateRequestDto = new CommentCreateRequestDto();
        commentCreateRequestDto.getUserName();
        commentCreateRequestDto.setRestaurantName("KFC");
        commentCreateRequestDto.setText(null);
        commentCreateRequestDto.setScore(2);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/N11/UserComments/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentCreateRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void when_Score_IsNull_RequestCheck() throws Exception {
        CommentCreateRequestDto commentCreateRequestDto = new CommentCreateRequestDto();
        commentCreateRequestDto.getUserName();
        commentCreateRequestDto.setRestaurantName("KFC");
        commentCreateRequestDto.setText("1000");
        commentCreateRequestDto.setScore(3);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/N11/UserComments/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentCreateRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenUserCommentRequestValidResponseIsSuccess() throws Exception {
        CommentCreateRequestDto commentCreateRequestDto = getCreateRequestDto();
        UserComment createdUserComment = new UserComment();
        createdUserComment.setText(commentCreateRequestDto.getText());
        createdUserComment.setScore(commentCreateRequestDto.getScore());
        Mockito.when(commentService.create(any(CommentCreateRequestDto.class))).thenReturn(createdUserComment);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/N11/UserComments/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentCreateRequestDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateUserComment() throws Exception {
        UserComment createdUserComment = getUserComments();
        CommentUpdateRequest commentUpdateRequest = new CommentUpdateRequest("40.7128", 4);
        createdUserComment.setId(10L);
        commentUpdateRequest.setText("10.5");
        commentUpdateRequest.setScore(5);
        Mockito.when(commentService.create(any())).thenReturn(createdUserComment);

        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/N11/UserComments/comments/" + createdUserComment.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentUpdateRequest)))
                .andExpect(status().isOk());

    }


    @Test
    public void testDeleteUserComment() throws Exception {
        UserComment createdUserComment = getUserComments();
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/N11/UserComments/comments/" + createdUserComment.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private UserComment getUserComments() {
        UserComment createComment = new UserComment();
        createComment.setId(2L);
        createComment.setText("1000");
        createComment.setScore(2);
        return createComment;
    }

    private CommentCreateRequestDto getCreateRequestDto() {
        CommentCreateRequestDto createRequestDto = new CommentCreateRequestDto();
        createRequestDto.setUserName("İsmail");
        createRequestDto.setRestaurantName("KFC");
        createRequestDto.setText("1000");
        createRequestDto.setScore(2);
        return createRequestDto;
    }

}
