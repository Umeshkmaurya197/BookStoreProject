package com.git.bookstore.entity;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@ToString
@NoArgsConstructor
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id", nullable = false)
    private Integer wishlistId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserData userData;
    @ElementCollection
    @CollectionTable(name = "wishlist_books", joinColumns = @JoinColumn(name = "wishlist_id"))
    private List<Integer> bookId;

    public Wishlist(Integer wishlistId, UserData userData, List<Integer> bookId) {
        this.wishlistId = wishlistId;
        this.userData = userData;
        this.bookId = bookId;
    }

    public Wishlist(UserData userData, List<Integer> bookIdList) {
        this.userData=userData;
        this.bookId=bookIdList;
    }
    public Integer getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Integer wishlistId) {
        this.wishlistId = wishlistId;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public List<Integer> getBookId() {
        return bookId;
    }

    public void setBookId(List<Integer> bookId) {
        this.bookId = bookId;
    }
}
