package com.diko.productmiddleware.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import java.net.URI;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExternalServiceException.class)
    public ProblemDetail handleExternalServiceException(ExternalServiceException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());

        problemDetail.setTitle("External Service Error");
        problemDetail.setType(URI.create("urn:product-middleware:error:external-service-empty"));
        problemDetail.setProperty("timestamp", LocalDateTime.now().toString());

        return problemDetail;
    }

    public ProblemDetail handleRestClientException(RestClientException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());

        problemDetail.setTitle("API Connection Error");
        problemDetail.setType(URI.create("urn:product-middleware:error:api-connection-failed"));
        problemDetail.setProperty("timestamp", LocalDateTime.now().toString());

        return problemDetail;
    }
}
