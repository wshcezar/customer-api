package com.customer.api.exception.handler;

import com.customer.api.exception.ResourceNotFoundException;
import com.customer.api.exception.ValidatorCustomerException;
import com.customer.api.exception.bundle.ExceptionMessageResource;
import com.customer.api.exception.model.ExceptionDataResponse;
import com.customer.api.exception.model.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final ExceptionMessageResource exceptionMessage;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ExceptionDataResponse> errorList = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            ExceptionResponse exceptionResponse =
                    new ExceptionResponse(
                            error.getField(),
                            error.getDefaultMessage(),
                            request.getDescription(false),
                            LocalDateTime.now());
            errorList.add(ExceptionDataResponse.builder().data(exceptionResponse).build());
        }
        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        ex.getMessage(),
                        ex.getMessage(),
                        request.getDescription(false),
                        LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidatorCustomerException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(ValidatorCustomerException ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        ex.getMessage(),
                        getMessageFormat(ex.getMessage(), ex.getMessageCustom()),
                        request.getDescription(false),
                        LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        ex.getMessage(),
                        exceptionMessage.findMessage(ex.getMessage()),
                        request.getDescription(false),
                        LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    private String getMessageFormat(final String message, final String messageCustom) {
        return messageCustom == null
                ? exceptionMessage.findMessage(message)
                : String.format(exceptionMessage.findMessage(message), messageCustom);
    }
}