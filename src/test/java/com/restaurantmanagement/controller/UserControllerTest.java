package com.restaurantmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantmanagement.entity.User;
import com.restaurantmanagement.model.UserCreateRequestDto;
import com.restaurantmanagement.model.UserUpdateRequest;
import com.restaurantmanagement.repository.UserRepository;
import com.restaurantmanagement.service.UserService;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;


import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void whenUserRequestIsNull() throws Exception {
        UserCreateRequestDto userCreateRequestDto = null;
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/N11/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreateRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void when_UserName_IsNull_RequestCheck() throws Exception {
        UserCreateRequestDto userCreateRequestDto = getCreateRequestDto();
        userCreateRequestDto.setName(null);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/N11/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreateRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void when_Surname_IsNull_RequestCheck() throws Exception {
        UserCreateRequestDto userCreateRequestDto = getCreateRequestDto();
        userCreateRequestDto.setSurName(null);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/N11/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreateRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void when_Latitude_IsNull_RequestCheck() throws Exception {
        UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto();
        userCreateRequestDto.setName("İsmail");
        userCreateRequestDto.setSurName("Doğan");
        userCreateRequestDto.setLatitude(null);
        userCreateRequestDto.setLongitude(-74.0060);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/N11/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreateRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void when_Longitude_IsNull_RequestCheck() throws Exception {
        UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto();
        userCreateRequestDto.setName("İsmail");
        userCreateRequestDto.setSurName("Doğan");
        userCreateRequestDto.setLatitude(40.7128);
        userCreateRequestDto.setLongitude(null);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/N11/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreateRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenUserRequestValidResponseIsSuccess() throws Exception {
        UserCreateRequestDto userCreateRequestDto = getCreateRequestDto();
        User createdUser = new User();
        createdUser.setName(userCreateRequestDto.getName());
        createdUser.setSurName(userCreateRequestDto.getSurName());
        createdUser.setLatitude(userCreateRequestDto.getLatitude());
        createdUser.setLongitude(userCreateRequestDto.getLongitude());
        Mockito.when(userService.create(any(UserCreateRequestDto.class))).thenReturn(createdUser);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/N11/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreateRequestDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateUser() throws Exception {
        User createdUser = getUsers();
        createdUser.setId(10L);
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest("Jane", "Doe", 40.7128, -74.0060);
        Mockito.when(userService.create(any())).thenReturn(createdUser);
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/N11/user/" + createdUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateRequest)))
                .andExpect(status().isOk());

    }

    @Test
    public void testDeleteUser() throws Exception {
        User createdUser = getUsers();
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/N11/user/" + createdUser.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    private User getUsers() {
        User createUser = new User();
        createUser.setId(5L);
        createUser.setName("İsmail");
        createUser.setSurName("Doğan");
        createUser.setLatitude(40.7128);
        createUser.setLongitude(-74.0060);
        return createUser;
    }

    private UserCreateRequestDto getCreateRequestDto() {
        UserCreateRequestDto createRequestDto = new UserCreateRequestDto();
        createRequestDto.setName("İsmail");
        createRequestDto.setSurName("Doğan");
        createRequestDto.setLatitude(40.7128);
        createRequestDto.setLongitude(-74.0060);
        return createRequestDto;
    }

}
