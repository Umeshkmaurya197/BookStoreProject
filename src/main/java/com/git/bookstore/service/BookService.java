package com.git.bookstore.service;

import com.git.bookstore.dto.BookDTO;
import com.git.bookstore.email.EmailService;
import com.git.bookstore.entity.BookData;
import com.git.bookstore.exception.CustomException;
import com.git.bookstore.repository.BookRepository;
import com.git.bookstore.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    TokenUtility tokenUtility;

    /*
        User can add new Books by this method
    */
    @Override
    public String addNewBook(BookDTO bookDTO) {
        BookData bookData = new BookData(bookDTO);
        bookRepository.save(bookData);
        String token = TokenUtility.createToken(bookData.getBookId());
     //   emailService.sendMail("umeshkmaurya22@gmail.com", "New book added successfully ", "New book added successfully ." + "\n New book details, " + "\n   Book Name         : " + bookDTO.bookName + "" + "\n   Author Name       : " + bookDTO.authorName + "" + "\n   Book Description : " + bookDTO.bookDescription + "" + "\n   Book Image         : " + bookDTO.bookImg + "" + "\n   Book Price          : $ " + bookDTO.price + "" + "\n   Book Quantity     : " + bookDTO.quantity + " .");
        return token;
    }

    /*
        Using this method User can get book data by book id
    */
    @Override
    public BookData getBookById(int bookId) {
       return bookRepository.findById(bookId).orElseThrow(() -> new CustomException(" Book id " + bookId + " not exist in data base  "));
    }

    /*
        This method will return List of all available books in book store
    */
    @Override
    public List<BookData> getAllBooks() {
        List<BookData> bookDataList = bookRepository.findAll();
        if (bookDataList.isEmpty()) {
            throw new CustomException(" Book list is Empty ");
        } else {
            return bookDataList;
        }
    }

    /*
        Using this method user can search any book by book name in list
    */
    @Override
    public Optional<BookData> getBooksByName(String bookName) {
        Optional<BookData> bookData = bookRepository.findBooksByName(bookName);
        if (bookData.isEmpty()) {
            throw new CustomException(" Book not found in the list");
        } else {
            return bookData;
        }
    }

    /*
        This method will delete any book by book name
    */
    @Override
    public String deleteBookByName(String bookName) {
        Optional<BookData> bookData = bookRepository.findBooksByName(bookName);
        int bookId = bookData.get().getBookId();
        if (bookData.isPresent()) {
            bookRepository.deleteById(bookId);
            return " Book deleted successfully ";
        } else {
            throw new CustomException(" Book Name " + bookName + " not found in data base");
        }
    }

    /*
        Using this method user can (update / change) book information by using id .
    */
    @Override
    public BookData updateBookById(String token, BookDTO bookDTO) {
        int bookId = tokenUtility.decodeToken(token);
        Optional<BookData> bookData = bookRepository.findById(bookId);
        if (bookData.isPresent()) {
            BookData newBookData = new BookData(bookDTO);
            newBookData.setBookId(bookId);
            return bookRepository.save(newBookData);
        } else {
            throw new CustomException(" Book id " + bookId + " not found in data base ");
        }
    }

    /*
        This method will update/change book Quantity i.e. No of book
    */
    @Override
    public BookData updateBookQuantity(String token, Integer bookQuantity) {
        int bookId = tokenUtility.decodeToken(token);
        Optional<BookData> bookData = bookRepository.findById(bookId);
        if (bookData.isPresent()) {
            BookData newBookData = bookRepository.getBookById(bookId);
            newBookData.setQuantity(bookQuantity);
            newBookData.setBookId(bookId);
            return bookRepository.save(newBookData);
        } else {
            throw new CustomException(" Book Id not found in data base ");
        }
    }

    /*
        Using this Method user can perform sorting based on book names in ascending order .
    */
    @Override
    public List<BookData> bookSortingByNameInAscending() {
        List<BookData> bookData = bookRepository.findAll();
        if (bookData.isEmpty()) {
            throw new CustomException("Book List is Empty in data base ");
        } else {
            return bookRepository.findAllBookByNameInAscending();
        }
    }

    /*
        Using this Method user can perform sorting based on book names in Descending order .
    */
    @Override
    public List<BookData> bookSortingByNameInDescending() {
        List<BookData> bookData = bookRepository.findAll();
        if (bookData.isEmpty()) {
            throw new CustomException("Book List is Empty in data base ");
        } else {
            return bookRepository.findAllBookByNameInDescending();
        }
    }

}

