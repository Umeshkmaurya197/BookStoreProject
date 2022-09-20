package com.git.bookstore.service;

import com.git.bookstore.dto.LoginDTO;
import com.git.bookstore.dto.UserDTO;
import com.git.bookstore.entity.UserData;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    String addUser(UserDTO userDTO);

    String userLogIn(LoginDTO loginDTO);

    UserData getUserById(String token);

    List<UserData> getUserByFirstName(String firstName);

    Optional<UserData> getUserByEmail(String email);

    List<UserData> getAllUsers();

    UserData changeUserPassword(String token, String password);

    UserData updateUserByEmail(String email, UserDTO userDTO);


}
