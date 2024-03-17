package com.restaurantmanagement.service;

import com.restaurantmanagement.entity.Restaurant;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {


    private final RestaurantService restaurantService;

    public RecommendationService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public List<Restaurant> getRecommendations(Double userLatitude, Double userLongitude) {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        List<Restaurant> nearbyRestaurants = restaurants.stream()
                .filter(r -> calculateDistance(userLatitude, userLongitude, r.getLatitude(), r.getLongitude()) <= 10.0)
                .collect(Collectors.toList());

        nearbyRestaurants.sort(Comparator.comparingDouble(r -> calculateDistance(userLatitude, userLongitude, r.getLatitude(), r.getLongitude())));

        List<Restaurant> recommendations = new ArrayList<>();
        double totalWeight = 0.0;
        for (Restaurant restaurant : nearbyRestaurants) {
            double distanceWeight = 1 - (calculateDistance(userLatitude, userLongitude, restaurant.getLatitude(), restaurant.getLongitude()) / 10.0);
            double totalScore = (0.7 * restaurant.getScore()) + (0.3 * distanceWeight);
            restaurant.setScore(totalScore);
            totalWeight += totalScore;
            recommendations.add(restaurant);
            if (recommendations.size() == 3) {
                break;
            }
        }


        for (Restaurant recommendation : recommendations) {
            recommendation.setScore(recommendation.getScore() / totalWeight);
        }

        return recommendations;
    }


    protected double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
        final int R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}

