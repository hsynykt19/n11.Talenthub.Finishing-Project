//package com.restaurantmanagement.service;
//
//
//import com.restaurantmanagement.entity.Restaurant;
//import com.restaurantmanagement.entity.RestaurantSolr;
//import com.restaurantmanagement.entity.UserComment;
//import com.restaurantmanagement.exception.RestaurantNotFoundException;
//import com.restaurantmanagement.model.RestaurantCreateRequestDto;
//import com.restaurantmanagement.model.RestaurantUpdateRequest;
//import com.restaurantmanagement.repository.CommentRepository;
//import com.restaurantmanagement.repository.RestaurantRepository;
//import com.restaurantmanagement.repository.RestaurantRepositorySolr;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class RestaurantServiceSolr {
//
//    @Autowired
//    private RestaurantRepositorySolr restaurantRepository;
//
//    public void save(RestaurantSolr restaurant) {
//        restaurantRepository.save(restaurant);
//    }
//
//    public List<RestaurantSolr> findAll() {
//        return (List<RestaurantSolr>) restaurantRepository.findAll();
//    }
//}
