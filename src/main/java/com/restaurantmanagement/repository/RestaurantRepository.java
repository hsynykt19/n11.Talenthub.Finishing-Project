package com.restaurantmanagement.repository;

import com.restaurantmanagement.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    Optional<Restaurant> findByName(String restaurantName);

    Optional<List<Restaurant>> findByAndLatitudeAndLongitude(Double userLatitude, Double userLongitude);

}
