package com.fraisdirect.exception;

import com.fraisdirect.utils.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ResponseVO<ValidationErrorResponse>> onConstraintValidationException(
            ConstraintViolationException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.getViolations().add(
                    new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        ResponseVO<ValidationErrorResponse> responseVO = new ResponseVOBuilder<ValidationErrorResponse>().addData(error).fail(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(responseVO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ResponseVO<ValidationErrorResponse>> onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(
                    new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        ResponseErrorVo<ValidationErrorResponse> responseErrorVo = new ResponseErrorVo<>("INVALID_DATA","les données ne sont pas valide");
        ResponseVO<ValidationErrorResponse> responseVO = new ResponseVOBuilder<ValidationErrorResponse>().addData(error).error(responseErrorVo,HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(responseVO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseVO<Void>> handleNotFoundException(EntityNotFoundException ex) {
        ResponseErrorVo errorVo = new ResponseErrorVo("ENTITY_NOT_FOUND", "L'entité introuvable", ex.getMessage());
        ResponseVO<Void> response = new ResponseVOBuilder<Void>().fail(HttpStatus.NOT_FOUND).error(errorVo,HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
   // @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseVO<Void>> handleGenericException(Exception ex) {
        ResponseErrorVo errorVo = new ResponseErrorVo("GENERIC_ERROR", "Erreur interne", ex.getMessage());
        ResponseVO<Void> response = new ResponseVOBuilder<Void>().fail(HttpStatus.INTERNAL_SERVER_ERROR).error(errorVo,HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
