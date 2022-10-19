package com.git.bookstore.service;

import com.git.bookstore.dto.LoginDTO;
import com.git.bookstore.dto.UserDTO;
import com.git.bookstore.email.EmailService;
import com.git.bookstore.entity.UserData;
import com.git.bookstore.exception.CustomException;
import com.git.bookstore.repository.UserRepository;
import com.git.bookstore.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenUtility tokenUtility;
    @Autowired
    EmailService emailService;

    //Creating new User Account
    @Override
    public UserData addUser(UserDTO userDTO) {
        UserData userData = new UserData(userDTO);
        //   emailService.sendMail(userDTO.email, "User successfully created", "Your account successfully created into Book Store ," + "\n\nyour details are :" + "\n User Id : " + userData.getUserId() + "" + "\n First Name : " + userData.getFirstName() + "" + "\n Last Name : " + userData.getLastName() + "" + "\n Email : " + userData.getEmail() + "" + "\n Address : " + userData.getAddress() + "" + "\n DOB : " + userData.getDOB() + "" + "\n password : " + userData.getPassword() + "" + "\n your id token is : " + token);
        return userRepository.save(userData);
    }

    // Login user
    @Override
    public String userLogIn(LoginDTO loginDTO) {
        UserData userDataExist = userRepository.findUserByLoginId(loginDTO.email).orElseThrow(() -> new CustomException("Entered user id " + loginDTO.email + " does not exist in data base "));
        if (userDataExist.getPassword().equals(loginDTO.getPassword())) {
            Integer userId = userDataExist.getUserId();
            String token = TokenUtility.createToken(userId);
            userRepository.save(userDataExist);
            return token;
        }
        return " Wrong password ";
    }

    //get user by his userId
    @Override
    public UserData getUserById(String token) {
        Integer userId = tokenUtility.decodeToken(token);
        UserData userData = userRepository.getUserById(userId);
        if (userData == null) {
            throw new CustomException("User id " + userId + " not found in data base ");
        } else {
            return userData;
        }
    }

    // get user data by user's first name
    @Override
    public List<UserData> getUserByFirstName(String fullName) {
        List<UserData> userData = userRepository.findUserByFirstName(fullName);
        if (userData.isEmpty()) {
            throw new CustomException("First name " + fullName + " not found in the data base ");
        } else {
            return userData;
        }
    }

    //get user data by email
    @Override
    public Optional<UserData> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findUserByEmail(email).orElseThrow(() -> new CustomException("First name " + email + " not found in the data base ")));

    }

    //get all users data from list
    @Override
    public List<UserData> getAllUsers() {
        return userRepository.findAll();
    }

    //change the password using token and password
    @Override
    public UserData changeUserPassword(String token, String password) {
        Integer userId = tokenUtility.decodeToken(token);
        UserData userData = userRepository.findById(userId).orElseThrow(() -> new CustomException("User id " + userId + " does not exist in data base ."));
        userData.setPassword(password);
        userRepository.save(userData);
        return userData;
    }

    //update user by email
    @Override
    public UserData updateUserByEmail(String email, UserDTO userDTO) {
        Optional<UserData> userData = userRepository.findUserByEmail(email);
        int userId = userData.get().getUserId();
        if (userData.isPresent()) {
            UserData userDTOData = new UserData(userDTO);
            userDTOData.setUserId(userId);
            return userRepository.save(userDTOData);
        } else {
            throw new CustomException("Email " + email + " does not exist in data base ");
        }
    }

}