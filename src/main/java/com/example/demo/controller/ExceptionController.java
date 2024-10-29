package com.example.demo.controller;

import com.example.demo.exception.Constants;
import com.example.demo.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class ExceptionController {
    
    @GetMapping("/customException")
    public void customException() throws CustomException {
        throw new CustomException(Constants.ExceptionClass.PRODUCT, HttpStatus.BAD_REQUEST, "customException 메소드 호출");
    }

    @GetMapping("/runtimeException")
    public void runtimeException() {
        throw new RuntimeException("runtimeException 메소드 호출");
    }

    
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> handlerException(
            RuntimeException e,
            HttpServletRequest request
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        
        log.error("클래스 내 handlerException 호출, {}, {}", request.getRequestURI(), e.getMessage());
        
        Map<String, String> map = new  HashMap<>();
        map.put("error type ", httpStatus.getReasonPhrase());
        map.put("code ", "400");
        map.put("message ", e.getMessage());
        
        return new ResponseEntity<Map<String, String>>(map, responseHeaders, httpStatus);
    }
    
}
