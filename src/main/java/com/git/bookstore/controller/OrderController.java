package com.git.bookstore.controller;

import com.git.bookstore.dto.OrderDTO;
import com.git.bookstore.dto.ResponseDTO;
import com.git.bookstore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/book-store/order")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @PostMapping("/place-order")
    public ResponseEntity<ResponseDTO> placeOrder(@Param(value = "token")String token, @RequestBody OrderDTO orderDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Order Successfully Placed",orderService.placeOrder(token,orderDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get-order-data")
    public ResponseEntity<ResponseDTO> getOrderData(@Param(value = "token")String token) {
        ResponseDTO responseDTO = new ResponseDTO("Order Details ",orderService.getOrderDetails(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
