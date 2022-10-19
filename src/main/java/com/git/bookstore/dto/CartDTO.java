package com.git.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CartDTO {

    public Integer userId;
    public List<Integer> bookId;
    public List<Integer> quantity;
}





