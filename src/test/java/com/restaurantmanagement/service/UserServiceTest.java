package com.restaurantmanagement.service;

import com.restaurantmanagement.entity.User;
import com.restaurantmanagement.model.UserCreateRequestDto;
import com.restaurantmanagement.model.UserUpdateRequest;
import com.restaurantmanagement.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void whenSaveUser_shouldReturnUser() {

        UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto();

        userCreateRequestDto.setName("Hüseyin");
        userCreateRequestDto.setSurName("Yakut");
        userCreateRequestDto.setLatitude(65.6584);
        userCreateRequestDto.setLongitude(-36.0965);

        User user = new User();

        user.setName(userCreateRequestDto.getName());
        user.setSurName(userCreateRequestDto.getSurName());
        user.setLongitude(userCreateRequestDto.getLongitude());
        user.setLatitude(userCreateRequestDto.getLatitude());

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User registeredUser = userService.create(userCreateRequestDto);
        assertNotNull(registeredUser);
        assertEquals(userCreateRequestDto.getName(), user.getName());

    }


    @Test
    public void testUpdateUser() {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("Hüseyin");
        existingUser.setSurName("Yakut");
        existingUser.setLatitude(40.7128);
        existingUser.setLongitude(-74.0060);

        User updateUser = new User();
        updateUser.setId(1L);
        updateUser.setName("İsmail");
        updateUser.setSurName("Doğan");
        updateUser.setLatitude(40.7128);
        updateUser.setLongitude(-74.0060);


        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setName("İsmail");
        userUpdateRequest.setSurname("Doğan");
        userUpdateRequest.setLatitude(40.7128);
        userUpdateRequest.setLongitude(-74.0060);

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(updateUser);

        User result = userService.update(1L, userUpdateRequest);

        assertNotNull(result);
        User user = result;
        assertEquals("İsmail", user.getName());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setId(1L);
        user.setName("İsmail");
        user.setSurName("Doğan");
        user.setLatitude(40.7128);
        user.setLongitude(-74.0060);


        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        userService.deleteUserById(user.getId());

        verify(userRepository).deleteById(user.getId());
    }

    @Test
    public void whenGivenId_shouldDeleteUser_ifFound() {
        User user = new User();
        user.setId(1L);
        user.setName("İsmail");
        user.setSurName("Doğan");
        user.setLatitude(40.7128);
        user.setLongitude(-74.0060);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        userService.deleteUserById(user.getId());
        verify(userRepository).deleteById(user.getId());
    }

}


