package com.git.bookstore.dto;

import com.git.bookstore.entity.UserData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data

@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    public String fullName;
    public String email;
    public String password;
    public Long mobileNumber;
}
