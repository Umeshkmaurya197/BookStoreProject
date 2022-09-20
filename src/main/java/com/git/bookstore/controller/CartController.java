package com.git.bookstore.controller;

import com.git.bookstore.dto.CartDTO;
import com.git.bookstore.dto.ResponseDTO;
import com.git.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book-store/cart")
public class CartController {
    @Autowired
    ICartService cartService;

    //Curl - http://localhost:8080/book-store/cart/add-cart?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoyfQ.S4iy11IshEhLyc1J5koLwTdRCKZdYFB8pvBq13oECwY
    @PostMapping("/add-cart")
    public ResponseEntity<ResponseDTO> addCart(@Param(value = "token") String token, @RequestBody CartDTO cartDTO) {
        ResponseDTO responseDTO = new ResponseDTO("your cart is ready ", cartService.addToCart(token, cartDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    //Curl - http://localhost:8080/book-store/cart/delete-cart-by-id/16
    @DeleteMapping("/delete-cart-by-id/{cartId}")
    public ResponseEntity<ResponseDTO> deleteCartById(@PathVariable int cartId) {
        ResponseDTO responseDTO = new ResponseDTO("Based on cart id " + cartId + " found data ", cartService.deleteCartById(cartId));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


}
