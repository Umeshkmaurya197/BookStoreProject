package com.git.bookstore.dto;

import com.git.bookstore.entity.UserData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    public String firstName;
    public String lastName;
    public String email;
    public String address;
    public LocalDate DOB;
    public String password;


    @Override
    public String toString() {
        return "UserDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", DOB=" + DOB +
                ", password='" + password + '\'' +
                '}';
    }
}
