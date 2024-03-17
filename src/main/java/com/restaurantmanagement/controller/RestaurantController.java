package com.restaurantmanagement.controller;


import com.restaurantmanagement.entity.Restaurant;
import com.restaurantmanagement.model.RestaurantCreateRequestDto;
import com.restaurantmanagement.model.RestaurantUpdateRequest;
import com.restaurantmanagement.service.RecommendationService;
import com.restaurantmanagement.service.RestaurantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@Api
@RestController
@RequestMapping("/N11/restaurants")
public class RestaurantController {



    private final RestaurantService restaurantService;
    private final RecommendationService recommendationService;

    public RestaurantController(RestaurantService restaurantService, RecommendationService recommendationService) {
        this.restaurantService = restaurantService;
        this.recommendationService = recommendationService;
    }

    @ApiOperation(value = "/N11/restaurants")
    @PostMapping("")
    public ResponseEntity<Object> createRestaurant(@Valid @RequestBody RestaurantCreateRequestDto restaurantDto) {
        restaurantService.create(restaurantDto);
        return new ResponseEntity<>("Restaurantınız başarı bir şekilde  eklendi", HttpStatus.OK);
    }
    @ApiOperation(value = "/N11/restaurants")
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Restaurant>> updateRestaurant(@PathVariable Long id,
                                                    @Valid @RequestBody RestaurantUpdateRequest restaurantUpdateRequest) {
        restaurantService.update(id, restaurantUpdateRequest);
        return ResponseEntity.ok().build();
    }
    @ApiOperation(value = "/N11/restaurants")
    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }
    @ApiOperation(value = "/N11/restaurants")
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Object> deleteRestaurant(@PathVariable Long restaurantId) {
        restaurantService.delete(restaurantId);
        return ResponseEntity.ok().build();
    }
    @ApiOperation(value = "/N11/restaurants/recommendations")
    @GetMapping("/recommendations")
    public List<Restaurant> getRecommendations(@RequestParam Double userLatitude,
                                               @RequestParam Double userLongitude) {
        return recommendationService.getRecommendations(userLatitude, userLongitude);
    }
}
