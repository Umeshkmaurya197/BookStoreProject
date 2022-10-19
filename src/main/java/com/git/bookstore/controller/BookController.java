package com.git.bookstore.controller;

import com.git.bookstore.dto.BookDTO;
import com.git.bookstore.dto.ResponseDTO;
import com.git.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
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
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable Integer bookId) {
        ResponseDTO responseDTO = new ResponseDTO("Book on book id found book data", bookService.getBookById(bookId));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //Curl - http://localhost:8080/book-store/book/get-all-books
    @GetMapping("/get-all-books")
    public ResponseEntity<ResponseDTO> getAllBooks() {
        ResponseDTO responseDTO = new ResponseDTO("List of all books found data", bookService.getAllBooks());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //Curl - http://localhost:8080/book-store/book/get-book-by-name/the Richest Man In Babylon
    @GetMapping("/get-book-by-name/{bookName}")
    public ResponseEntity<ResponseDTO> getBooksByName(@PathVariable String bookName) {
        ResponseDTO responseDTO = new ResponseDTO("List of all books of same names ", bookService.getBooksByName(bookName));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete-book-by-name/{bookName}")
    public ResponseEntity<ResponseDTO> deleteBooksByName(@PathVariable String bookName) {
        // Curl - http://localhost:8080/book-store/book/delete-book-by-name/Rich Dad Poor Dad
        ResponseDTO responseDTO = new ResponseDTO("List of all books of same names ", bookService.deleteBookByName(bookName));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //Curl - http://localhost:8080/book-store/book/update-book-by-id/2
    @PutMapping("/update-book-by-id/{bookId}")
    public ResponseEntity<ResponseDTO> updateBookById(@PathVariable Integer bookId, @RequestBody BookDTO bookDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Updated book details  ", bookService.updateBookById(bookId,bookDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //Curl - http://localhost:8080/book-store/book/update-book-quantity/1/100
    @PutMapping("/update-book-quantity/{bookId}/{bookQuantity}")
    public ResponseEntity<ResponseDTO> updateBookQuantity(@PathVariable Integer bookId, @PathVariable Integer bookQuantity) {
        ResponseDTO responseDTO = new ResponseDTO("Updated book quantity ", bookService.updateBookQuantity(bookId , bookQuantity));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //Curl - http://localhost:8080/book-store/book/book-sorting-by-name-ascending
    @GetMapping("/book-sorting-by-name-ascending")
    public ResponseEntity<ResponseDTO> bookSortingByNameInAscending() {
        ResponseDTO responseDTO = new ResponseDTO("Sorted books by name in ascending order ", bookService.bookSortingByNameInAscending());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //Curl - http://localhost:8080/book-store/book/book-sorting-by-name-descending
    @GetMapping("/book-sorting-by-name-descending")
    public ResponseEntity<ResponseDTO> bookSortingByNameInDescending() {
        ResponseDTO responseDTO = new ResponseDTO("Sorted books by name in descending order ", bookService.bookSortingByNameInDescending());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //Curl - http://localhost:8080/book-store/book/book-sorting-by-price-ascending
    @GetMapping("/book-sorting-by-price-ascending")
    public ResponseEntity<ResponseDTO> bookSortingByPriceInAscending() {
        ResponseDTO responseDTO = new ResponseDTO("Sorted books by price in ascending order ", bookService.bookSortingByPriceInAscending());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //Curl - http://localhost:8080/book-store/book/book-sorting-by-price-descending
    @GetMapping("/book-sorting-by-price-descending")
    public ResponseEntity<ResponseDTO> bookSortingByPriceInDescending() {
        ResponseDTO responseDTO = new ResponseDTO("Sorted books by price in descending order ", bookService.bookSortingByPriceInDescending());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //Curl - http://localhost:8080/book-store/book/book-sorting-by-newest-arrival
    @GetMapping("/book-sorting-by-newest-arrival")
    public ResponseEntity<ResponseDTO> bookSortingByNewestArrival() {
        ResponseDTO responseDTO = new ResponseDTO("Sorted books by newest arrival books ", bookService.bookSortingByNewestArrival());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
