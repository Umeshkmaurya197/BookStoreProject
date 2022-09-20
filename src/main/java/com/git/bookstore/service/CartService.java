package com.git.bookstore.service;

import com.git.bookstore.dto.CartDTO;
import com.git.bookstore.entity.BookData;
import com.git.bookstore.entity.Cart;
import com.git.bookstore.entity.UserData;
import com.git.bookstore.exception.CustomException;
import com.git.bookstore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    IBookService bookService;
    @Autowired
    IUserService userService;

    @Override
    public Cart addToCart(String token, CartDTO cartDTO) {
        ArrayList<BookData> bookList = new ArrayList<>();
        UserData userData = userService.getUserById(token);
        int userId = userData.getUserId();
        int cartId;
        Cart cart = new Cart();
        if (cartRepository.findCartByUserId(userId) == null) {
            List<Integer> bookIdList = cartDTO.bookId;
            List<Integer> quantities = cartDTO.quantity;
            float totalPrice = 0.0f;
            for (int i = 0; i < bookIdList.size(); i++) {
                if (quantities.get(i) <= bookService.getBookById(bookIdList.get(i)).getQuantity()) {
                    bookList.add(bookService.getBookById(bookIdList.get(i)));
                    totalPrice += bookService.getBookById(bookIdList.get(i)).getPrice() * (quantities.get(i));
                } else {
                    throw new CustomException("please select small quantity than our stock ");
                }
                cart = new Cart(userData, cartDTO.getBookId(), cartDTO.getQuantity());
            }
        } else {
            cartId = cartRepository.findCartByUserId(userId).getCartId();
            cart = new Cart(cartId, userData, cartDTO.getBookId(), cartDTO.getQuantity());
        }
        return cartRepository.save(cart);
    }


    @Override
    public String deleteCartById(int cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if (cart.isPresent()) {
            cartRepository.deleteById(cartId);
            return "Data successfully deleted ";
        } else {
            throw new CustomException("Cart  not found");
        }
    }
}




