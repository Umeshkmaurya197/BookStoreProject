package com.git.bookstore.service;

import com.git.bookstore.dto.BookDTO;
import com.git.bookstore.entity.BookData;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    BookData addNewBook(BookDTO bookDTO);

    BookData getBookById(Integer bookId);

//    List<BookData> getBookByCartId(Integer cartId);

    List<BookData> getAllBooks();

    Optional<BookData> getBooksByName(String bookName);

    String deleteBookByName(String bookName);

    BookData updateBookById(Integer bookId, BookDTO bookDTO);

    BookData updateBookQuantity(Integer bookId, Integer bookQuantity);

    List<BookData> bookSortingByNameInAscending();

    List<BookData> bookSortingByNameInDescending();

    List<BookData> bookSortingByPriceInAscending();

    List<BookData> bookSortingByPriceInDescending();

    List<BookData> bookSortingByNewestArrival();

}
