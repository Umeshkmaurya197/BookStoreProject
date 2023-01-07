package com.git.bookstore.repository;

import com.git.bookstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query(value = "SELECT * FROM book_store.order WHERE cart_id=:cartId",nativeQuery = true)
    List<Order> getOrderByCartId(@Param(value="cartId")Integer cartId);
}
