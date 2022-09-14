package com.git.bookstore.exception;


import com.git.bookstore.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    /*
         this Exception handler method use to handle Exception
    */
    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseDTO> getUserByIdException(CustomException customException) {
        ResponseDTO responseDTO = new ResponseDTO("Exception while searching for User", customException.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }
}
