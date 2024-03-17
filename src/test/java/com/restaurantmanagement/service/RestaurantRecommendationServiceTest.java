package com.restaurantmanagement.service;

import com.restaurantmanagement.entity.Restaurant;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class RestaurantRecommendationServiceTest {
    @InjectMocks
    private RecommendationService recommendationService;
    @Mock
    private RestaurantService restaurantService;

    @Test
    public void testGetRecommendations() {
        // Mock data
        Double userLatitude = 40.7128;
        Double userLongitude = -74.0060;
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(01l, "Restaurant1", 40.7128, -74.0060, 4.5)); // Distance: 0 km
        restaurants.add(new Restaurant(02l, "Restaurant2", 40.7143, -74.0062, 3.8)); // Distance: ~0.2 km
        restaurants.add(new Restaurant(03l, "Restaurant3", 40.7151, -74.0059, 4.2)); // Distance: ~0.4 km
        restaurants.add(new Restaurant(04l, "Restaurant4", 40.7160, -74.0063, 4.0)); // Distance: ~0.6 km
        restaurants.add(new Restaurant(05l, "Restaurant5", 40.7190, -74.0055, 4.8)); // Distance: ~1.0 km

        when(restaurantService.getAllRestaurants()).thenReturn(restaurants);

        recommendationService.calculateDistance(userLatitude, userLongitude, 40.7128, -74.0060); // Distance: 0 km
        recommendationService.calculateDistance(userLatitude, userLongitude, 40.7143, -74.0062); // Distance: ~0.2 km
        recommendationService.calculateDistance(userLatitude, userLongitude, 40.7151, -74.0059); // Distance: ~0.4 km
        recommendationService.calculateDistance(userLatitude, userLongitude, 40.7160, -74.0063); // Distance: ~0.6 km
        recommendationService.calculateDistance(userLatitude, userLongitude, 40.7190, -74.0055); // Distance: ~1.0 km

        // Test
        List<Restaurant> recommendations = recommendationService.getRecommendations(userLatitude, userLongitude);
        System.out.println(recommendations);
        // Assert
        assertEquals(3, recommendations.size());
        assertEquals("Restaurant1", recommendations.get(0).getName());
        assertEquals("Restaurant2", recommendations.get(1).getName());
        assertEquals("Restaurant3", recommendations.get(2).getName());
    }
}
