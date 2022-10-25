package com.git.bookstore.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WishlistDTO {

    public Integer userId;
    public List<Integer> bookId;
}
