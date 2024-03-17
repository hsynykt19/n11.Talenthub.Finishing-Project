package com.restaurantmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantmanagement.entity.Restaurant;
import com.restaurantmanagement.model.RestaurantCreateRequestDto;
import com.restaurantmanagement.model.RestaurantUpdateRequest;
import com.restaurantmanagement.service.RecommendationService;
import com.restaurantmanagement.service.RestaurantService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @MockBean
    private RecommendationService recommendationService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void whenRestaurantRequestIsNull() throws Exception {
        RestaurantCreateRequestDto restaurantCreateRequestDto = null;
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/N11/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurantCreateRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void when_UserName_IsNull_RequestCheck() throws Exception {
        RestaurantCreateRequestDto restaurantCreateRequestDto = getCreateRequestDto();
        restaurantCreateRequestDto.setName(null);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/N11/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurantCreateRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void when_Latitude_IsNull_RequestCheck() throws Exception {
        RestaurantCreateRequestDto restaurantCreateRequestDto = new RestaurantCreateRequestDto();
        restaurantCreateRequestDto.setName("İsmail");
        restaurantCreateRequestDto.setLatitude(null);
        restaurantCreateRequestDto.setLongitude(-74.0060);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/N11/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurantCreateRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void when_Longitude_IsNull_RequestCheck() throws Exception {
        RestaurantCreateRequestDto restaurantCreateRequestDto = new RestaurantCreateRequestDto();
        restaurantCreateRequestDto.setName("İsmail");
        restaurantCreateRequestDto.setLatitude(40.7128);
        restaurantCreateRequestDto.setLongitude(null);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/N11/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurantCreateRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenRestaurantRequestValidResponseIsSuccess() throws Exception {
        RestaurantCreateRequestDto restaurantCreateRequestDto = getCreateRequestDto();
        Restaurant createdRestaurant = new Restaurant();
        createdRestaurant.setName(restaurantCreateRequestDto.getName());
        createdRestaurant.setLatitude(restaurantCreateRequestDto.getLatitude());
        createdRestaurant.setLongitude(restaurantCreateRequestDto.getLongitude());
        createdRestaurant.setScore(3.2);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/N11/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurantCreateRequestDto)))
                .andExpect(status().isOk());
    }
    @Test
    public void testGetAllRestaurants() throws Exception {
        Restaurant restaurantKfc = new Restaurant();
        restaurantKfc.setName("Restaurant KFC");
        Restaurant restaurantBurger = new Restaurant();
        restaurantBurger.setName("Restaurant Burger");
        List<Restaurant> restaurantList = Arrays.asList(restaurantKfc, restaurantBurger);

        when(restaurantService.getAllRestaurants()).thenReturn(restaurantList);

        MvcResult result =  this.mockMvc.perform(get("/N11/restaurants")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(restaurantList));
    }

    @Test
    public void testUpdateRestaurant() throws Exception {
        Restaurant createdRestaurant = getRestaurants();
        createdRestaurant.setId(10L);
        RestaurantUpdateRequest updateRequest =new  RestaurantUpdateRequest();
        updateRequest.setLatitude(10.5);
        updateRequest.setLongitude(5.0);
        updateRequest.setName("updateRestaurant");
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/N11/restaurants/" + createdRestaurant.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk());

    }

    @Test
    public void testDeleteRestaurant() throws Exception {
        Restaurant createdRestaurant = getRestaurants();
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/N11/restaurants/" + createdRestaurant.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private Restaurant getRestaurants() {
        Restaurant createRestaurant = new Restaurant();
        createRestaurant.setId(5L);
        createRestaurant.setName("İsmail");
        createRestaurant.setLatitude(40.7128);
        createRestaurant.setLongitude(-74.0060);
        return createRestaurant;
    }

    private RestaurantCreateRequestDto getCreateRequestDto() {
        RestaurantCreateRequestDto createRequestDto = new RestaurantCreateRequestDto();
        createRequestDto.setName("İsmail");
        createRequestDto.setLatitude(40.7128);
        createRequestDto.setLongitude(-74.0060);
        return createRequestDto;
    }


}
