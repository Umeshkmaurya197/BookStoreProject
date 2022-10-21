package com.git.bookstore.service;

import com.git.bookstore.dto.CartDTO;
import com.git.bookstore.entity.BookData;
import com.git.bookstore.entity.Cart;
import com.git.bookstore.entity.UserData;
import com.git.bookstore.exception.CustomException;
import com.git.bookstore.repository.BookRepository;
import com.git.bookstore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    IBookService bookService;
    @Autowired
    IUserService userService;

    @Autowired
    BookRepository bookRepository;

    @Override
    public Cart addToCart(String token, CartDTO cartDTO) {
        ArrayList<BookData> bookList = new ArrayList<>();
        UserData userData = userService.getUserById(token);
        Integer userId = userData.getUserId();
        Integer cartId;
        Cart cart = cartRepository.findCartByUserId(userId);
//        List<Integer> bookQuantities =cart.getQuantity();
        System.out.println("--Out side of every thing ==> 1");
        if (cart == null) {
            List<Integer> bookIdList = cartDTO.bookId;
            List<Integer> quantities = cartDTO.quantity;
//            System.out.println("====List Of Quantity in data base ====> "+ bookQuantities);
//            float totalPrice = 0.0f;
            System.out.println("--Inside of if cart find then ==> 2");
            for (int i = 0; i < bookIdList.size(); i++) {
                System.out.println("--Inside of for loop  and outside of if quantities ==> 3");
                if (quantities.get(i) <= bookService.getBookById(bookIdList.get(i)).getQuantity()) {
                    System.out.println("inside of if and before of add book id and quantities ==> 4");
                    bookList.add(bookService.getBookById(bookIdList.get(i)));
                    bookList.add(bookService.getBookById(quantities.get(i)));
                    System.out.println("inside of if and after of add book id and quantities ==> 5");
//                    totalPrice += bookService.getBookById(bookIdList.get(i)).getPrice() * (quantities.get(i));
//                    System.out.println("---totalPrice ==> 6   : " + totalPrice);
                } else {
                    throw new CustomException("please select small quantity than our stock ");
                }
                System.out.println("out of if of quantities ==> 7 ");
                cart = new Cart(userData, cartDTO.getBookId(), cartDTO.getQuantity());
            }
        } else {
            System.out.println("if cart already found ==> 8 ");
            cartId = cartRepository.findCartByUserId(userId).getCartId();
            cart = new Cart(cartId, userData, cartDTO.getBookId(), cartDTO.getQuantity());
        }
        System.out.println("before saving cart in repository ==> 9 " + cart.toString());
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartByUserId(String token) {
        UserData userData = userService.getUserById(token);
        Integer userId = userData.getUserId();
        Cart cart = cartRepository.findCartByUserId(userId);
        if (cart != null) {
            return cart;
        } else throw new CustomException(" cart id not found ");
    }

    @Override
    public List<BookData> getCartBooksByUserId(String token) {
        UserData userData = userService.getUserById(token);
        Integer userId = userData.getUserId();
        Cart cart = cartRepository.findCartByUserId(userId);
        Integer cartId = cart.getCartId();
        List<BookData> bookDataList = bookRepository.getBookByCartId(cartId);
        if (cart != null) {
            if (bookDataList != null) {
                return bookDataList;
            } else throw new CustomException("book not found in the cart ");
        } else {
            throw new CustomException("cart not found in the cart ");
        }
    }

    @Override
    public String deleteCartByUserId(String token) {
        UserData userData = userService.getUserById(token);
        Integer userId = userData.getUserId();
        Cart cart = cartRepository.findCartByUserId(userId);
        Integer cartId = cart.getCartId();
        if (cart != null) {
            cartRepository.deleteById(cartId);
            return "Cart Deleted";
        } else throw new CustomException("cart not Found");
    }

    @Override
    public Cart increaseCartQuantityByUserId(String token, Integer bookIdentity) {
        UserData userData = userService.getUserById(token);
        Integer userid = userData.getUserId();
        Cart cart = cartRepository.findCartByUserId(userid);
        if (cart != null) {
            List<Integer> bookIdList = cart.getBookId();
            List<Integer> quantityList = cart.getQuantity();
            System.out.println(" BookId List 1 ==>> : " + bookIdList);
            System.out.println(" Book Quantity List 2 ==>> : " + quantityList);
            int quantityIndex = bookIdList.indexOf(bookIdentity);
            System.out.println(" book bookIdentity 2.5 ==>> : " + bookIdentity);
            System.out.println(" Quantity Index 3 ==>> : " + quantityIndex);
            for (int i = 0; i <= bookIdList.size(); i++) {
                if (i == quantityIndex) {
                    System.out.println(" index i 4 ==>> : " + i);
                    quantityList.set(i, quantityList.get(i) + 1);
                }
                System.out.println(" book Quantity List 5 ==>> : " + quantityList);
            }
            System.out.println(" book Quantity List 6==>> : " + cart);
            return cartRepository.save(cart);
        } else throw new CustomException("Cart is null");
    }

    @Override
    public Cart decreaseCartQuantityByUserId(String token, Integer bookIdentity) {
        UserData userData = userService.getUserById(token);
        Integer userid = userData.getUserId();
        Cart cart = cartRepository.findCartByUserId(userid);
        if (cart != null) {
            List<Integer> bookIdList = cart.getBookId();
            List<Integer> quantityList = cart.getQuantity();
            int quantityIndex = bookIdList.indexOf(bookIdentity);
            for (int i = 0; i <= bookIdList.size(); i++) {
                if (i == quantityIndex) {
                    if (quantityList.get(i) > 1) {
                        quantityList.set(i, quantityList.get(i) - 1);
                    }
                }
            }
            return cartRepository.save(cart);
        } else throw new CustomException("Cart is null");
    }

    @Override
    public Cart removeBookFromCartByUserId(String token, Integer bookIdentity) {
        UserData userData = userService.getUserById(token);
        Integer userid = userData.getUserId();
        Cart cart = cartRepository.findCartByUserId(userid);
        if (cart != null) {
            List<Integer> bookIdList = cart.getBookId();
            List<Integer> quantityList = cart.getQuantity();
            System.out.println(" BookId List 1 ==>> : " + bookIdList);
            System.out.println(" Book Quantity List 2 ==>> : " + quantityList);
            int bookIndex = bookIdList.indexOf(bookIdentity);
            System.out.println(" book bookIdentity 2.5 ==>> : " + bookIdentity);
            System.out.println(" Quantity Index 3 ==>> : " + bookIndex);
            for (int i = 0; i <= bookIdList.size(); i++) {
                if (i == bookIndex) {
                    bookIdList.remove(i);
                    quantityList.remove(i);
                }
            }
            return cartRepository.save(cart);
        } else throw new CustomException("Cart not found");
    }
}







