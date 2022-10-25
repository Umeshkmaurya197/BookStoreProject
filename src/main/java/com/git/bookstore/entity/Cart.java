package com.git.bookstore.entity;

import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@ToString
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private Integer cartId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserData userData;
    @ElementCollection
    @CollectionTable(name = "cart_books", joinColumns = @JoinColumn(name = "cart_id"))
    public List<Integer> bookId;
    @ElementCollection
    @CollectionTable(name = "cart_book_quantities", joinColumns = @JoinColumn(name = "cart_id"))
    public List<Integer> quantity;
    public Cart() {
    }

    public Cart(Integer cartId, UserData userData, List<Integer> bookId ,List<Integer> quantity) {
        this.cartId = cartId;
        this.userData = userData;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public Cart(UserData userData, List<Integer> bookId, List<Integer> quantity) {
        this.userData = userData;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public List<Integer> getBookId() {
        return bookId;
    }

    public void setBookId(List<Integer> bookId) {
        this.bookId = bookId;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }

}
