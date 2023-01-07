package com.git.bookstore.service;

import com.git.bookstore.dto.OrderDTO;
import com.git.bookstore.entity.Order;

import java.util.List;

public interface IOrderService {

    Order placeOrder(String token, OrderDTO orderDTO);

    List<Order> getOrderDetails(String token);
}
