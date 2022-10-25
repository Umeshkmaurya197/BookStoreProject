package com.git.bookstore.repository;

import com.git.bookstore.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

    @Query(value = "Select * From wishlist e where e.user_id = :userId", nativeQuery = true)
    Wishlist findWishlistByUserId(@Param(value = "userId") Integer userId);
}
