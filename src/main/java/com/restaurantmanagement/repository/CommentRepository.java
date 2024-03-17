package com.restaurantmanagement.repository;

import com.restaurantmanagement.entity.Restaurant;
import com.restaurantmanagement.entity.UserComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<UserComment,Long> {

    Optional<List<UserComment>> findByAndAndRestaurant(Restaurant restaurantId);
}
