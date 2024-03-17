package com.restaurantmanagement.controller;

import com.restaurantmanagement.entity.User;
import com.restaurantmanagement.model.UserCreateRequestDto;
import com.restaurantmanagement.model.UserUpdateRequest;
import com.restaurantmanagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api
@RestController
@RequestMapping("/N11/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "/N11/user")
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserCreateRequestDto userDto) {
        User user = userService.create(userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @ApiOperation(value = "/N11/user/{id}")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser( @PathVariable Long id,
                                            @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
      User user=  userService.update(id, userUpdateRequest);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @ApiOperation(value = "/N11/user/{id}")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }


}
