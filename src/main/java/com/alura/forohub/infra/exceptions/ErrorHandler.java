package com.alura.forohub.infra.exceptions;

import com.alura.forohub.commons.constants.ApiConstants;
import com.alura.forohub.infra.exceptions.models.ErrorResponse;
import com.alura.forohub.infra.exceptions.models.ErrorType;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
@SuppressWarnings("all")
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ErrorResponse resolvedException(HttpServletRequest req, Exception ex) {
        log.error("Error: ",ex);
        return ErrorResponse.builder()
                .type(ErrorType.FATAL.name())
                .details(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .location(req.getRequestURI())
                .uuid(req.getHeader(ApiConstants.UUID))
                .build();
    }

    @ExceptionHandler(NullPointerException.class)
    public ErrorResponse resolvedNullPointerException(HttpServletRequest req, NullPointerException ex) {
        log.error("Error: ",ex);
        return ErrorResponse.builder()
                .type(ErrorType.FATAL.name())
                .details(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .uuid(req.getHeader(ApiConstants.UUID))
                .build();
    }

    @ExceptionHandler(ServletException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorResponse resolvedServletException(HttpServletRequest req, ServletException ex) {
        log.error("Error: ",ex);
        return ErrorResponse.builder()
                .type(ErrorType.ERROR.name())
                .code(String.valueOf(HttpStatus.FORBIDDEN.value()))
                .details(ApiConstants.GENERIC_ERROR)
                .timestamp(LocalDateTime.now())
                .uuid(req.getHeader(ApiConstants.UUID))
                .build();
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorResponse resolvedIOException(HttpServletRequest req, IOException ex) {
        log.error("Error: ",ex);
        return ErrorResponse.builder()
                .type(ErrorType.ERROR.name())
                .code(String.valueOf(HttpStatus.FORBIDDEN.value()))
                .details(ApiConstants.GENERIC_ERROR)
                .timestamp(LocalDateTime.now())
                .uuid(req.getHeader(ApiConstants.UUID))
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse resolvedMethodArgumentNotValidException(
            HttpServletRequest httpServletRequest,
            MethodArgumentNotValidException exception
    ){
        log.error("Error :: " + exception.getMessage());
        return ErrorResponse.builder()
                .type(ErrorType.ERROR.name())
                .code(ApiConstants.BAD_REQUEST)
                .details(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .location(httpServletRequest.getRequestURI())
                .uuid(httpServletRequest.getHeader(ApiConstants.UUID.toLowerCase()))
                .build();
    }

    @ExceptionHandler(TokenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorResponse tokenException(
            HttpServletRequest httpServletRequest,
            TokenException exception
    ){
        log.error("Error :: " + exception.getMessage());
        return ErrorResponse.builder()
                .type(ErrorType.ERROR.name())
                .code(String.valueOf(HttpStatus.FORBIDDEN.value()))
                .details(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .location(httpServletRequest.getRequestURI())
                .uuid(httpServletRequest.getHeader(ApiConstants.UUID.toLowerCase()))
                .build();
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse validationException(
            HttpServletRequest httpServletRequest,
            ValidationException exception
    ){
        log.error("Error :: " + exception.getMessage());
        return ErrorResponse.builder()
                .type(ErrorType.ERROR.name())
                .code(ApiConstants.BAD_REQUEST)
                .details(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .location(httpServletRequest.getRequestURI())
                .uuid(httpServletRequest.getHeader(ApiConstants.UUID.toLowerCase()))
                .build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse resolvedEntityNotFoundException(
            HttpServletRequest httpServletRequest,
            EntityNotFoundException exception
    ){
        log.error("Error :: " + exception.getMessage());
        return ErrorResponse.builder()
                .type(ErrorType.ERROR.name())
                .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .details(exception.getMessage())
                .moreInfo("No se encontró el tópico con el ID ingresado")
                .timestamp(LocalDateTime.now())
                .location(httpServletRequest.getRequestURI())
                .uuid(httpServletRequest.getHeader(ApiConstants.UUID.toLowerCase()))
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse resolvedMethodArgumentTypeMismatchException(
            HttpServletRequest httpServletRequest,
            MethodArgumentTypeMismatchException exception
    ){
        log.error("Error :: " + exception.getMessage());
        return ErrorResponse.builder()
                .type(ErrorType.ERROR.name())
                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .details(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .location(httpServletRequest.getRequestURI())
                .uuid(httpServletRequest.getHeader(ApiConstants.UUID.toLowerCase()))
                .build();
    }



}
