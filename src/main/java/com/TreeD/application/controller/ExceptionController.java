package com.TreeD.application.controller;

import com.TreeD.application.exceptions.CommentNotFoundException;
import com.TreeD.application.exceptions.ModelNotFoundException;
import com.TreeD.application.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity userNotFoundException(UserNotFoundException unfe){
        //todo: implement functionality
        return null;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity modelNotFoundException(ModelNotFoundException mnfe){
        //todo: implement functionality
        return null;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity commentNotFoundException(CommentNotFoundException cnfe){
        //todo: implement functionality
        return null;
    }
}
