package com.example.demo.exception;


import org.springframework.http.HttpStatus;


public class CustomException extends Exception {
    private final Constants.ExceptionClass exceptionClass;
    private final HttpStatus httpStatus;

    public CustomException (Constants.ExceptionClass exceptionClass, HttpStatus httpStatus, String message) {
        super(exceptionClass.toString() + message);

        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }

    public Constants.ExceptionClass getExceptionClass() {
        return this.exceptionClass;
    }

    public int getHttpStatusCode() {
        return this.httpStatus.value();
    }

    public String getHttpStatusType() {
        return this.httpStatus.getReasonPhrase();
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

}
