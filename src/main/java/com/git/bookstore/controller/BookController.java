package com.git.bookstore.controller;

import com.git.bookstore.dto.BookDTO;
import com.git.bookstore.dto.ResponseDTO;
import com.git.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book-store/book")
public class BookController {

    @Autowired
    IBookService bookService;

    //Curl - http://localhost:8080/book-store/book/add-new-book
    @PostMapping("/add-new-book")
    public ResponseEntity<ResponseDTO> addNewBook(@RequestBody BookDTO bookDTO) {
        ResponseDTO responseDTO = new ResponseDTO("New book successfully added. ", bookService.addNewBook(bookDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    //Curl - http://localhost:8080/book-store/book/get-book-by-id/1
    @GetMapping("/get-book-by-id/{bookId}")
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable int bookId) {
        ResponseDTO responseDTO = new ResponseDTO("Book on book id found book data", bookService.getBookById(bookId));
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    //Curl - http://localhost:8080/book-store/book/get-all-books
    @GetMapping("/get-all-books")
    public ResponseEntity<ResponseDTO> getAllBooks() {
        ResponseDTO responseDTO = new ResponseDTO("List of all books found data", bookService.getAllBooks());
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    //Curl - http://localhost:8080/book-store/book/get-book-by-name/the Richest Man In Babylon
    @GetMapping("/get-book-by-name/{bookName}")
    public ResponseEntity<ResponseDTO> getBooksByName(@PathVariable String bookName) {
        ResponseDTO responseDTO = new ResponseDTO("List of all books of same names ", bookService.getBooksByName(bookName));
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    // Curl - http://localhost:8080/book-store/book/delete-book-by-name/Rich Dad Poor Dad
    @DeleteMapping("/delete-book-by-name/{bookName}")
    public ResponseEntity<ResponseDTO> deleteBooksByName(@PathVariable String bookName) {
        ResponseDTO responseDTO = new ResponseDTO("List of all books of same names ", bookService.deleteBookByName(bookName));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //Curl - http://localhost:8080/book-store/book/update-book-by-id?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjozfQ.QkW4UdVXPaAtq9vpKLrXerNr-dWnjvmvvn5NL-e7ZjM
    @PutMapping("/update-book-by-id")
    public ResponseEntity<ResponseDTO> updateBookById(@Param(value = "token") String token, @RequestBody BookDTO bookDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Updated book details  ", bookService.updateBookById(token, bookDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    //Curl - http://localhost:8080/book-store/book/update-book-quantity/100?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoyfQ.S4iy11IshEhLyc1J5koLwTdRCKZdYFB8pvBq13oECwY
    @PutMapping("/update-book-quantity/{bookQuantity}")
    public ResponseEntity<ResponseDTO> updateBookQuantity(@Param(value = "token") String token, @PathVariable Integer bookQuantity) {
        ResponseDTO responseDTO = new ResponseDTO("Updated book quantity ", bookService.updateBookQuantity(token, bookQuantity));
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }
    //Curl - http://localhost:8080/book-store/book/book-sorting-by-name-ascending
    @GetMapping("/book-sorting-by-name-ascending")
    public ResponseEntity<ResponseDTO> bookSortingByNameInAscending() {
        ResponseDTO responseDTO = new ResponseDTO("Sorted books by name in ascending order ", bookService.bookSortingByNameInAscending());
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }
    //Curl - http://localhost:8080/book-store/book/book-sorting-by-name-descending
    @GetMapping("/book-sorting-by-name-descending")
    public ResponseEntity<ResponseDTO> bookSortingByNameInDescending() {
        ResponseDTO responseDTO = new ResponseDTO("Sorted books by name in descending order ", bookService.bookSortingByNameInDescending());
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }
}
