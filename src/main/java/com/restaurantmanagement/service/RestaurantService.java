package com.restaurantmanagement.service;


import com.restaurantmanagement.entity.Restaurant;
import com.restaurantmanagement.entity.UserComment;
import com.restaurantmanagement.exception.RestaurantNotFoundException;
import com.restaurantmanagement.model.RestaurantCreateRequestDto;
import com.restaurantmanagement.model.RestaurantUpdateRequest;
import com.restaurantmanagement.repository.CommentRepository;
import com.restaurantmanagement.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final CommentRepository commentRepository;
    private final RestaurantRepository restaurantRepository;

    public void create(RestaurantCreateRequestDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDto.getName());
        restaurant.setLatitude(restaurantDto.getLatitude());
        restaurant.setLongitude(restaurantDto.getLongitude());
        restaurant.setScore(restaurantDto.getRating());
        restaurantRepository.save(restaurant);
    }

    public void update(Long id, RestaurantUpdateRequest restaurantUpdateRequest) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        restaurant.setName(restaurantUpdateRequest.getName());
        restaurant.setLatitude(restaurantUpdateRequest.getLatitude());
        restaurant.setLongitude(restaurantUpdateRequest.getLongitude());
        restaurantRepository.save(restaurant);

    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll().stream().map(restaurant -> Restaurant.builder()
                        .longitude(restaurant.getLongitude())
                        .latitude(restaurant.getLatitude())
                        .id(restaurant.getId())
                        .score(restaurant.getScore())
                        .name(restaurant
                                .getName()).build())
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant bilgisi bulunamadı"));
        List<UserComment> userComments = commentRepository.findByAndAndRestaurant(restaurant).orElse(null);
        if (userComments.size() > 0) {
            commentRepository.deleteAll(userComments);
        }
        restaurantRepository.delete(restaurant);

    }

    public Restaurant getRestaurant(String restaurantName) {
        return restaurantRepository.findByName(restaurantName).orElse(null);

    }
}
