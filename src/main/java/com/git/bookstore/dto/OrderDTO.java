package com.git.bookstore.dto;

import com.git.bookstore.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    public Cart cart;
    public Float totalPrice;
    public String pin;
    public String locality;
    public String address;
    public String city;
    public String landmark;
    public String type;
    public boolean cancel;

}
