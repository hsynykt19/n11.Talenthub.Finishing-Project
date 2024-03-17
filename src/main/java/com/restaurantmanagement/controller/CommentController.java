package com.restaurantmanagement.controller;

import com.restaurantmanagement.entity.UserComment;
import com.restaurantmanagement.model.CommentCreateRequestDto;
import com.restaurantmanagement.model.CommentUpdateRequest;
import com.restaurantmanagement.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
@Api
@RestController
@RequestMapping("/N11/UserComments")
public class CommentController {


    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "/N11/UserComments/comments")
    @PostMapping(value = "/comments")
    public ResponseEntity<Object> createComment(@Valid @RequestBody CommentCreateRequestDto commentDto) {
        commentService.create(commentDto);
        return new ResponseEntity<>("Yorumunuz başarı bir şekilde eklendi", HttpStatus.OK);

    }
    @ApiOperation(value = "/N11/UserComments/comments")
    @PutMapping("/comments/{id}")
    public ResponseEntity<String> updateComment(@PathVariable Long id,
                                                        @Valid   @RequestBody CommentUpdateRequest commentUpdateRequest) {
        Optional<UserComment> update = commentService.update(id, commentUpdateRequest);
        return new ResponseEntity<>("Yorumunuz başarı bir şekilde güncellendi", HttpStatus.OK);

    }
    @ApiOperation(value = "/N11/UserComments/comments")
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>("Yorumunuz başarı bir şekilde silindi", HttpStatus.OK);


    }

}

