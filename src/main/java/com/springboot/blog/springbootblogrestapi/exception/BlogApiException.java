package com.springboot.blog.springbootblogrestapi.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException {

    private HttpStatus httpstatus;
    private String message;

    public BlogApiException(HttpStatus httpStatus, String message) {
        this.httpstatus = httpStatus;
        this.message = message;
    }

    public BlogApiException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpstatus = httpStatus;
        this.message = message1;
    }

    public HttpStatus getHttpStatus() {
        return httpstatus;
    }
    
    public String getMessage() {
        return message;
    }
    
}
