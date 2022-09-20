package com.git.bookstore.service;

import com.git.bookstore.dto.CartDTO;
import com.git.bookstore.entity.Cart;

public interface ICartService {
    Cart addToCart(String token, CartDTO cartDTO);

    String deleteCartById(int cartId);
}

