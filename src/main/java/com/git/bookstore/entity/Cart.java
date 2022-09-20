package com.git.bookstore.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private int cartId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserData userData;
    @ElementCollection
    @CollectionTable(name = "cart_books", joinColumns = @JoinColumn(name = "cart_id"))
    public List<Integer> bookData;
    @ElementCollection
    @CollectionTable(name = "cart_book_quantities", joinColumns = @JoinColumn(name = "cart_id"))
    public List<Integer> quantity;

    public Cart() {
    }

    public Cart(int cartId, UserData userData, List<Integer> bookData, List<Integer> quantity) {
        this.cartId = cartId;
        this.userData = userData;
        this.bookData = bookData;
        this.quantity = quantity;
    }

    public Cart(UserData userData, List<Integer> bookId, List<Integer> quantity) {
        this.userData = userData;
        this.bookData = bookId;
        this.quantity = quantity;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public List<Integer> getBookData() {
        return bookData;
    }

    public void setBookData(List<Integer> bookData) {
        this.bookData = bookData;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }
}
