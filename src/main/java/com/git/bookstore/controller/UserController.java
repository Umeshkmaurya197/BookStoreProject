package com.git.bookstore.controller;

import com.git.bookstore.dto.LoginDTO;
import com.git.bookstore.dto.ResponseDTO;
import com.git.bookstore.dto.UserDTO;
import com.git.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book-store/user")
public class UserController {

    @Autowired
    IUserService userService;

    //Curl - http://localhost:8080/book-store/user/register
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> addUser(@RequestBody UserDTO userDTO) {
        ResponseDTO responseDTO = new ResponseDTO("User account successfully created", userService.addUser(userDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    //Curl - http://localhost:8080/book-store/user/get-user-by-id/1
    @GetMapping("/get-user-by-id/{userId}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable Long userId) {
        ResponseDTO responseDTO = new ResponseDTO("Based on user id " + userId + " found data ", userService.getUserById(userId));
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    //Curl - http://localhost:8080/book-store/user/get-user-by-first-name/omi
    @GetMapping("/get-user-by-first-name/{firstName}")
    public ResponseEntity<ResponseDTO> getUserByFirstName(@PathVariable String firstName) {
        ResponseDTO responseDTO = new ResponseDTO("Based on user name " + firstName + " found data", userService.getUserByFirstName(firstName));
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    //Curl - http://localhost:8080/book-store/user/get-user-by-email/umeshkmaurya22@gmail.com
    @GetMapping("/get-user-by-email/{email}")
    public ResponseEntity<ResponseDTO> getUserByEmail(@PathVariable String email) {
        ResponseDTO responseDTO = new ResponseDTO("Based on email " + email + " found data ", userService.getUserByEmail(email));
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    //Curl - http://localhost:8080/book-store/user/get-all-users
    @GetMapping("/get-all-users")
    public ResponseEntity<ResponseDTO> getAllUsers() {
        ResponseDTO responseDTO = new ResponseDTO("List of all Users ", userService.getAllUsers());
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    //Curl - http://localhost:8080/book-store/user/change-password?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoyfQ.S4iy11IshEhLyc1J5koLwTdRCKZdYFB8pvBq13oECwY&password=Signature7
    @PutMapping("/change-password")
    public ResponseEntity<ResponseDTO> changePasswordByToken(@Param(value = "token") String token, @Param(value = "password") String password) {
        ResponseDTO responseDTO = new ResponseDTO("User password changed successfully ", userService.changeUserPassword(token, password));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //Curl - http://localhost:8080/book-store/user/update-user-by-email/umeshkumaurya22@gmail.com
    @PutMapping("/update-user-by-email/{email}")
    public ResponseEntity<ResponseDTO> updateUserByEmail(@PathVariable String email, @RequestBody UserDTO userDTO) {
        ResponseDTO responseDTO = new ResponseDTO("User account updated successfully by email ", userService.updateUserByEmail(email, userDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //Curl - http://localhost:8080/book-store/user/user-log-in
    @PostMapping("/user-log-in")
    public ResponseEntity<ResponseDTO> userLogInByTokenAndPassword(@RequestBody LoginDTO loginDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Welcome to home page ", userService.userLogIn(loginDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
