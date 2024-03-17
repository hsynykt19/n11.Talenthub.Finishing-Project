//package com.restaurantmanagement.controller;
//import com.restaurantmanagement.entity.RestaurantSolr;
//import com.restaurantmanagement.service.RestaurantServiceSolr;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/restaurants")
//public class RestaurantControllerSolr {
//
//    @Autowired
//    private RestaurantServiceSolr restaurantService;
//
//    @PostMapping
//    public ResponseEntity<Object> createRestaurant(@RequestBody RestaurantSolr restaurant) {
//        restaurantService.save(restaurant);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping
//    public ResponseEntity<List<RestaurantSolr>> getAllRestaurants() {
//        List<RestaurantSolr> restaurants = restaurantService.findAll();
//        return ResponseEntity.ok(restaurants);
//    }
//}
