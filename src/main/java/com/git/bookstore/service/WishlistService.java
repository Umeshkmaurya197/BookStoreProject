package com.git.bookstore.service;

import com.git.bookstore.dto.WishlistDTO;
import com.git.bookstore.entity.BookData;
import com.git.bookstore.entity.Cart;
import com.git.bookstore.entity.UserData;
import com.git.bookstore.entity.Wishlist;
import com.git.bookstore.exception.CustomException;
import com.git.bookstore.repository.BookRepository;
import com.git.bookstore.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService implements IWishlistService {

    @Autowired
    WishlistRepository wishlistRepository;
    @Autowired
    IUserService userService;
    @Autowired
    IBookService bookService;

    @Autowired
    BookRepository bookRepository;

//    @Override
//    public Wishlist addToWishList(Integer bookId, String token) {
//        UserData userData = userService.getUserById(token);
//        BookData bookData = bookService.getBookById(bookId);
//        if (userData != null) {
//            if (bookData != null) {
//                WishlistDTO wishlistDTO = new WishlistDTO(userData, bookData);
//                Wishlist wishlist = new Wishlist(wishlistDTO);
//                return wishlistRepository.save(wishlist);
//            } else {
//                throw new CustomException("Book not found");
//            }
//        } else {
//            throw new CustomException("User not found");
//        }
//    }

    @Override
    public Wishlist addToWishList(String token, WishlistDTO wishlistDTO) {
        UserData userData = userService.getUserById(token);
        Integer userId = userData.getUserId();
        Wishlist wishlist= wishlistRepository.findWishlistByUserId(userId);
        List<Integer> bookIdList = wishlistDTO.getBookId();
        if (userData != null) {
            if (wishlist != null) {
                Integer wishlistId=wishlist.getWishlistId();
                Wishlist wishlistData =new Wishlist(wishlistId,userData,bookIdList);
                return wishlistRepository.save(wishlistData);
            } else {
                Wishlist newWishlist = new Wishlist(userData,bookIdList);
                return wishlistRepository.save(newWishlist);
            }
        } else throw new CustomException("User not found");
    }

    @Override
    public Wishlist getWishlistByUserId(String token) {
        UserData userData = userService.getUserById(token);
        Integer userId = userData.getUserId();
        Wishlist wishlist = wishlistRepository.findWishlistByUserId(userId);
        if (wishlist != null) {
            return wishlist;
        } else throw new CustomException(" Wishlist id not found ");
    }

    @Override
    public List<BookData> getWishListBooksByUserId(String token) {
        UserData userData = userService.getUserById(token);
        System.out.println("userData 1 ====>  "+userData.toString());
        Integer userId = userData.getUserId();
        System.out.println("userId 2 =====> "+userId);
        Wishlist wishlist = wishlistRepository.findWishlistByUserId(userId);
        System.out.println("wish List 3 =====> "+wishlist);
        Integer wishlistId = wishlist.getWishlistId();
        System.out.println("wishlistId 4 =====>  "+wishlistId);
        List<BookData> bookDataWishList = bookRepository.getBookByWishlistId(wishlistId);
        System.out.println("book List from wishlist 5 ====>  "+bookDataWishList);
        if (userData != null) {
            if (bookDataWishList != null) {
                System.out.println("book List from wishlist inside of if condition  6 ====>  "+bookDataWishList);
               return bookDataWishList;
            } else throw new CustomException("Wishlist not found ");
        } else throw new CustomException("User not found");
    }

    @Override
    public Wishlist removeBookFromWishlist(Integer bookIdentity, String token) {
        UserData userData = userService.getUserById(token);
        Integer userId = userData.getUserId();
        Wishlist wishlist = wishlistRepository.findWishlistByUserId(userId);
        if(userData!=null){
            if(wishlist!=null){
                List<Integer> bookIdList = wishlist.getBookId();
                int bookIndex = bookIdList.indexOf(bookIdentity);
                for(int i=0; i<bookIdList.size();i++){
                    if(i==bookIndex){
                        bookIdList.remove(i);
                    }
                }
                return wishlistRepository.save(wishlist);
            }else throw new CustomException("Wish list not found");
        }else throw new CustomException("User not found ");

    }
}
