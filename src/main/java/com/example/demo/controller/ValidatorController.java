package com.example.demo.controller;

import com.example.demo.dto.ValidRequestDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ValidatorController {

    @PostMapping("/validator")
    public ResponseEntity<String> validator(
            @Valid @RequestBody ValidRequestDTO validRequestDTO
    ) {
        log.info(validRequestDTO.toString());
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

}
