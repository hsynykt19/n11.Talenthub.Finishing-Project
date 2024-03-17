package com.restaurantmanagement.service;


import com.restaurantmanagement.entity.User;
import com.restaurantmanagement.exception.UserNotFoundException;
import com.restaurantmanagement.model.UserCreateRequestDto;
import com.restaurantmanagement.model.UserUpdateRequest;
import com.restaurantmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(UserCreateRequestDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurName(userDto.getSurName());
        user.setLongitude(userDto.getLongitude());
        user.setLatitude(userDto.getLatitude());
        userRepository.save(user);
        return user;
    }

    public User update(Long id, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(id).orElseThrow();
        user.setName(userUpdateRequest.getName());
        user.setSurName(userUpdateRequest.getSurname());
        user.setLatitude(userUpdateRequest.getLatitude());
        user.setLongitude(userUpdateRequest.getLongitude());
        return userRepository.save(user);

    }

    public void deleteUserById(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);

    }


    public User getUser(String userName) {
        return userRepository.findByName(userName).orElse(null);

    }
}
