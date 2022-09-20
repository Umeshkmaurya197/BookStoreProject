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

//    @Query(value = "DELETE FROM book_store.book_data e where e.book_name=:bookName", nativeQuery = true)
//    Optional<BookData> deleteBookByName(@Param(value = "bookName") String bookName);

    @Query(value = "SELECT * FROM book_store.book_data e where e.book_id=:bookId", nativeQuery = true)
    BookData getBookById(@Param(value = "bookId") int bookId);

    @Query(value = "SELECT * FROM book_store.book_data e ORDER BY e.book_name ASC", nativeQuery = true)
    List<BookData> findAllBookByNameInAscending();

    @Query(value = "SELECT * FROM book_store.book_data e ORDER BY e.book_name DESC", nativeQuery = true)
    List<BookData> findAllBookByNameInDescending();
}
