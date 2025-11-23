package com.gdg.spring_study.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // 모든 컨트롤러의 예외를 감지하는 관제탑
public class GlobalExceptionHandler {

    // 1. @Valid 검증 실패 시 발생하는 예외 (MethodArgumentNotValidException)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // 발생한 모든 에러를 꺼내서 { "필드명": "에러메시지" } 형태로 만듭니다.
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // 2. 서비스 로직 등에서 잘못된 인자가 들어왔을 때 (IllegalArgumentException)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        // 404 Not Found 상태 코드와 함께 에러 메시지를 반환
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}