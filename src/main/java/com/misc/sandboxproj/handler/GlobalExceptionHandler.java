package com.misc.sandboxproj.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.misc.sandboxproj.execeptions.NotFoundException;
import com.misc.sandboxproj.execeptions.ValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> HandleValidation(ValidationException E)
    {
        return ResponseEntity.badRequest().body(E.getMessage());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleMaxSize(MaxUploadSizeExceededException E) 
    {
        return ResponseEntity.badRequest().body("File too large (max 10MB)");
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> HandleNotFound(NotFoundException E)
    {
        return ResponseEntity.status(404).body(E.getMessage());
    }
}
