package com.git.bookstore.repository;

import com.git.bookstore.entity.BookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookData, Integer> {
    @Query(value = "SELECT * FROM book_store.book_data e where e.book_name=:bookName", nativeQuery = true)
    Optional<BookData> findBooksByName(@Param(value = "bookName") String bookName);

    @Query(value = "SELECT * FROM book_store.book_data e where e.book_id=:bookId", nativeQuery = true)
    BookData getBookById(@Param(value = "bookId") Integer bookId);

    // to get books based on user cartId
    @Query(value = "SELECT b.* FROM book_data b INNER JOIN cart_books c ON b.book_id=c.book_id AND c.cart_id=:cartId", nativeQuery = true)
    List<BookData> getBookByCartId(@Param(value = "cartId") Integer cartId);

    @Query(value = "select b.* from book_data b INNER JOIN wishlist_books w ON b.book_id=w.book_id AND w.wishlist_id = :wishlistId", nativeQuery = true)
    List<BookData> getBookByWishlistId(@Param(value = "wishlistId") Integer wishlistId);


    @Query(value = "SELECT * FROM book_store.book_data e ORDER BY e.book_name ASC", nativeQuery = true)
    List<BookData> findAllBookByNameInAscending();

    @Query(value = "SELECT * FROM book_store.book_data e ORDER BY e.book_name DESC", nativeQuery = true)
    List<BookData> findAllBookByNameInDescending();

    @Query(value = "SELECT * FROM book_store.book_data e ORDER BY e.price ASC", nativeQuery = true)
    List<BookData> findAllBookByPriceInAscending();

    @Query(value = "SELECT * FROM book_store.book_data e ORDER BY e.price DESC", nativeQuery = true)
    List<BookData> findAllBookByPriceInDescending();

    @Query(value = "SELECT * FROM book_store.book_data e ORDER BY e.book_id DESC", nativeQuery = true)
    List<BookData> findAllBookByNewestArrival();


}
