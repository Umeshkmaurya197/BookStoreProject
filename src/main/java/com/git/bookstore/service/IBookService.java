package com.git.bookstore.service;

import com.git.bookstore.dto.BookDTO;
import com.git.bookstore.entity.BookData;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    String addNewBook(BookDTO bookDTO);

    BookData getBookById(String token);

    List<BookData> getAllBooks();

    Optional<BookData> getBooksByName(String bookName);

    String deleteBookByName(String bookName);

    BookData updateBookById(String token, BookDTO bookDTO);

    BookData updateBookQuantity(String token, Integer bookQuantity);

    List<BookData> bookSortingByNameInAscending();

    List<BookData> bookSortingByNameInDescending();
}
