package com.git.bookstore.service;


import com.git.bookstore.dto.WishlistDTO;
import com.git.bookstore.entity.BookData;
import com.git.bookstore.entity.Wishlist;

import java.util.List;

public interface IWishlistService {

    Wishlist addToWishList(String token, WishlistDTO wishlistDTO);

    Wishlist getWishlistByUserId(String token);
    List<BookData> getWishListBooksByUserId(String token);
    Wishlist removeBookFromWishlist(Integer bookId, String token);
}
