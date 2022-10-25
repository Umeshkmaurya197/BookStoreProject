package com.git.bookstore.controller;

import com.git.bookstore.dto.ResponseDTO;
import com.git.bookstore.dto.WishlistDTO;
import com.git.bookstore.service.IWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("book-store/wishlist")
public class WishlistController {

    @Autowired
    IWishlistService wishlistService;

    //Curl - http://localhost:8080/book-store/wishlist/add-to-wishlist
    @PostMapping("/add-to-wishlist")
    public ResponseEntity<ResponseDTO> addToWishlist(@Param(value = "token") String token, @RequestBody WishlistDTO wishlistDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Book Added To wishlist ", wishlistService.addToWishList(token,wishlistDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    //Curl - http://localhost:8080/book-store/wishlist/get-wishlist
    @GetMapping("/get-wishlist")
    public ResponseEntity<ResponseDTO> getWishlist(@Param(value = "token") String token) {
        ResponseDTO responseDTO = new ResponseDTO("your wishlist ", wishlistService.getWishlistByUserId(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //Curl - http://localhost:8080/book-store/wishlist/get-wishlist-books-by-user-id
    @GetMapping("/get-wishlist-books-by-user-id")
    public ResponseEntity<ResponseDTO> getWishlistBookByUserId(@Param(value = "token") String token) {
        ResponseDTO responseDTO = new ResponseDTO("All books from your wishlist ", wishlistService.getWishListBooksByUserId(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    //Curl - http://localhost:8080/book-store/wishlist/remove-book-from-wishlist/1
    @DeleteMapping("/remove-book-from-wishlist/{bookId}")
    public ResponseEntity<ResponseDTO> removeBookFromWishlist(@PathVariable Integer bookId, @Param(value = "token") String token) {
        ResponseDTO responseDTO = new ResponseDTO("book removed from your wishlist ", wishlistService.removeBookFromWishlist(bookId, token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
