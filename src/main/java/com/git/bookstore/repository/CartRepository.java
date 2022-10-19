package com.git.bookstore.repository;

import com.git.bookstore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query(value = "Select * From cart e where e.user_id = :userId", nativeQuery = true)
    Cart findCartByUserId(@Param(value = "userId") Integer userId);

}
