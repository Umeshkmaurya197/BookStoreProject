package com.git.bookstore.service;

import com.git.bookstore.dto.CartDTO;
import com.git.bookstore.entity.BookData;
import com.git.bookstore.entity.Cart;

import java.util.List;

public interface ICartService {
    Cart addToCart(String token, CartDTO cartDTO);

    Cart getCartByUserId(String token);

    List<BookData> getCartBooksByUserId(String token);

    String deleteCartByUserId(String token);
    Cart increaseCartQuantityByUserId(String token, Integer bookIdentity);

    Cart decreaseCartQuantityByUserId(String token, Integer bookIdentity);

    Cart removeBookFromCartByUserId(String token, Integer bookIdentity);
}

