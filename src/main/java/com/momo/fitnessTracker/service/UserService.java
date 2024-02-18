package com.momo.fitnessTracker.service;

import com.momo.fitnessTracker.Dto.UserCredentialsDto;
import com.momo.fitnessTracker.Dto.UserDetailsDto;
import com.momo.fitnessTracker.Dto.UserDto;
import com.momo.fitnessTracker.exception.UserNotFoundException;
import com.momo.fitnessTracker.model.User;
import org.springframework.data.jpa.repository.Query;

import java.io.IOException;
import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto) throws IOException;

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public UserDto findByUsername(String username) throws UserNotFoundException;

    List<User> showAllUser();

    void updateUserDetails(UserDto userDto, UserDetailsDto userDetailsDto);

    void updateUserCredentials(UserDto existingUser, UserCredentialsDto userCredentialsDto);
}
