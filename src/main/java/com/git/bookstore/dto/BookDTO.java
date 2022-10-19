package com.git.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    public String bookName;
    public String authorName;
    public String bookDescription;
    public String bookImg;
    public Float price;
    public Integer quantity;

}
