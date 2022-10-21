package com.git.bookstore.controller;

import com.git.bookstore.dto.CartDTO;
import com.git.bookstore.dto.ResponseDTO;
import com.git.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/book-store/cart")
public class CartController {
    @Autowired
    ICartService cartService;

    //Curl - http://localhost:8080/book-store/cart/add-cart
    @PostMapping("/add-cart")
    public ResponseEntity<ResponseDTO> addCart(@Param(value = "token") String token, @RequestBody CartDTO cartDTO) {
        ResponseDTO responseDTO = new ResponseDTO("your cart is ready ", cartService.addToCart(token, cartDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    //Curl - http://localhost:8080/book-store/cart/get-cart-books-by-user-id
    @GetMapping("/get-cart-books-by-user-id")
    public ResponseEntity<ResponseDTO> getCartBooksByUserId(@Param(value = "token") String token) {
        ResponseDTO responseDTO = new ResponseDTO("Based on user cart Books  ", cartService.getCartBooksByUserId(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //Curl - http://localhost:8080/book-store/cart/get-cart-by-user-id
    @GetMapping("/get-cart-by-user-id")
    public ResponseEntity<ResponseDTO> getCartByUserId(@Param(value = "token") String token) {
        ResponseDTO responseDTO = new ResponseDTO("Based on user cart Books  ", cartService.getCartByUserId(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //Curl - http://localhost:8080/book-store/cart/delete-cart-by-user-id
    @DeleteMapping("/delete-cart-by-user-id")
    public ResponseEntity<ResponseDTO> deleteCartByUserId(@Param(value = "token") String token) {
        ResponseDTO responseDTO = new ResponseDTO("Based on user cart Books  ", cartService.deleteCartByUserId(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //Curl - http://localhost:8080/book-store/cart/increase-cart-quantity-by-user-id/2
    @GetMapping("/increase-cart-quantity-by-user-id/{bookId}")
    public ResponseEntity<ResponseDTO> increaseQuantityByUserId(@Param(value = "token") String token,@PathVariable Integer bookId) {
        ResponseDTO responseDTO = new ResponseDTO("Based on user cart Books  ", cartService.increaseCartQuantityByUserId(token, bookId));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //Curl - http://localhost:8080/book-store/cart/decrease-cart-quantity-by-user-id/2
    @GetMapping("/decrease-cart-quantity-by-user-id/{bookIdentity}")
    public ResponseEntity<ResponseDTO> decreaseQuantityByUserId(@Param(value = "token") String token,@PathVariable Integer bookIdentity) {
        ResponseDTO responseDTO = new ResponseDTO("Based on user cart Books  ", cartService.decreaseCartQuantityByUserId(token, bookIdentity));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //Curl - http://localhost:8080/book-store/cart/remove-book-from-cart-by-user-id/2
    @GetMapping("/remove-book-from-cart-by-user-id/{bookIdentity}")
    public ResponseEntity<ResponseDTO> removeBookFromCartByUserId(@Param(value = "token") String token,@PathVariable Integer bookIdentity) {
        ResponseDTO responseDTO = new ResponseDTO("Based on user cart Books  ", cartService.removeBookFromCartByUserId(token, bookIdentity));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }



}
