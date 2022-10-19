package com.git.bookstore.entity;

import com.git.bookstore.dto.LoginDTO;
import com.git.bookstore.dto.UserDTO;

import javax.persistence.*;

@Entity
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    private String fullName;
    private String email;
    private String password;
    private Long mobileNumber;

    public UserData() {
    }

    public UserData(UserDTO userDTO) {
        this.fullName = userDTO.fullName;
        this.email = userDTO.email;
        this.password = userDTO.password;
        this.mobileNumber = userDTO.mobileNumber;
    }
    public UserData(LoginDTO loginDTO) {
        this.email = loginDTO.email;
        this.password = loginDTO.password;
    }
    public UserData(Integer userId, String fullName, String email, String password, Long mobileNumber) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
