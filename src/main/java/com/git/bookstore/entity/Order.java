package com.git.bookstore.entity;

import com.git.bookstore.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Integer orderId;
    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    private Float totalPrice;
    private String pin;
    private String locality;
    private String address;
    private String city;
    private String landmark;
    private String type;
    private LocalDate date;
    private boolean cancel;


    public Order(OrderDTO orderDTO) {
        this.cart = orderDTO.cart;
        this.totalPrice = orderDTO.totalPrice;
        this.pin = orderDTO.pin;
        this.locality = orderDTO.locality;
        this.address = orderDTO.address;
        this.city = orderDTO.city;
        this.landmark = orderDTO.landmark;
        this.type = orderDTO.type;
        this.cancel = orderDTO.cancel;
    }


    public Order(Cart cart, Float totalPrice, String pin, String locality, String address, String city, String landmark, String type, LocalDate date, boolean cancel) {
        this.cart = cart;
        this.totalPrice = totalPrice;
        this.pin = pin;
        this.locality = locality;
        this.address = address;
        this.city = city;
        this.landmark = landmark;
        this.type = type;
        this.date = date;
        this.cancel = cancel;
    }

    public Order(Integer orderId, Cart cart, Float totalPrice, String pin, String locality, String address, String city, String landmark, String type, LocalDate date, boolean cancel) {
        this.orderId = orderId;
        this.cart = cart;
        this.totalPrice = totalPrice;
        this.pin = pin;
        this.locality = locality;
        this.address = address;
        this.city = city;
        this.landmark = landmark;
        this.type = type;
        this.date = date;
        this.cancel = cancel;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

