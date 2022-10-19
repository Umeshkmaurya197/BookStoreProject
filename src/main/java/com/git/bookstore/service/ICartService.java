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
    List<Integer> increaseCartQuantityByUserId(String token, Integer bookId, List<Integer> quantity);
}

