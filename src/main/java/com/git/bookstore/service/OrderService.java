package com.git.bookstore.service;

import com.git.bookstore.dto.OrderDTO;
import com.git.bookstore.entity.Cart;
import com.git.bookstore.entity.Order;
import com.git.bookstore.entity.UserData;
import com.git.bookstore.exception.CustomException;
import com.git.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    IUserService userService;
    @Autowired
    ICartService cartService;
    @Autowired
    IBookService bookService;

    @Override
    public Order placeOrder(String token, OrderDTO orderDTO) {
        UserData userData = userService.getUserById(token);
        Integer userId = userData.getUserId();
        Cart cart = cartService.getCartByUserId(token);
        Integer cartId = cart.getCartId();
        System.out.println(cart.toString());
        if (userData!=null) {
            if (cart!=null) {
                float totalPrice = 0;
                float bookPrice = 0;
                List<Integer> bookIdList =cart.bookId;
                List<Integer> cartBookQuantity =cart.quantity;
                for(int i=0;i< bookIdList.size();i++){
                    if(orderDTO.getCart().getQuantity().get(i)<bookService.getBookById(bookIdList.get(i)).getPrice()){
                        Integer bookId =orderDTO.getCart().getBookId().get(i);
                         bookPrice =bookService.getBookById(bookId).getPrice();
                        Integer bookQuantity=orderDTO.getCart().getQuantity().get(i);
                         totalPrice += bookPrice*bookQuantity;
                         orderDTO.setTotalPrice(totalPrice);
                    }
                }
                Order order = new Order(orderDTO);
                order.setDate(LocalDate.now());
                return orderRepository.save(order);
            } else {
                throw new CustomException("Cart id Not Found");
            }
        } else throw new CustomException("User id not found.");
    }
}

