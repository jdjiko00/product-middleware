package com.diko.productmiddleware.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import java.net.URI;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExternalServiceException.class)
    public ProblemDetail handleExternalServiceException(ExternalServiceException ex) {

        log.warn("Problem with external API. Message: {}", ex.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.SERVICE_UNAVAILABLE,
                ex.getMessage());

        problemDetail.setTitle("External Service Error");
        problemDetail.setType(URI.create("urn:product-middleware:error:external-service-empty"));
        problemDetail.setProperty("timestamp", LocalDateTime.now().toString());

        return problemDetail;
    }

    @ExceptionHandler(RestClientException.class)
    public ProblemDetail handleRestClientException(RestClientException ex) {

        log.warn("Network error during communication with DummyJSON. Message: {}", ex.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.SERVICE_UNAVAILABLE,
                ex.getMessage());

        problemDetail.setTitle("API Connection Error");
        problemDetail.setType(URI.create("urn:product-middleware:error:api-connection-failed"));
        problemDetail.setProperty("timestamp", LocalDateTime.now().toString());

        return problemDetail;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ProblemDetail handleProductNotFoundException(ProductNotFoundException ex) {

        log.warn("User has requested product that doesn't exist. Message: {}", ex.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage());

        problemDetail.setTitle("Product Not Found");
        problemDetail.setType(URI.create("urn:product-middleware:error:product-not-found"));
        problemDetail.setProperty("timestamp", LocalDateTime.now().toString());

        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUncaughtException(Exception ex) {

        log.error("Unexpected internal server error. Message: {}", ex.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected error");

        problemDetail.setTitle("Internal Server Error");
        problemDetail.setType(URI.create("urn:product-middleware:error:internal-server-error"));
        problemDetail.setProperty("timestamp", LocalDateTime.now().toString());

        return problemDetail;
    }
}
