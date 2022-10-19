package com.git.bookstore.service;

import com.git.bookstore.dto.OrderDTO;
import com.git.bookstore.entity.Order;

public interface IOrderService {

    Order placeOrder(String token,OrderDTO orderDTO);
}
