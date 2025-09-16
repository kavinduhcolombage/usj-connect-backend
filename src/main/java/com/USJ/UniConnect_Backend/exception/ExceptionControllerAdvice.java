package com.USJ.UniConnect_Backend.exception;

import com.USJ.UniConnect_Backend.util.ErrorInfo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @Autowired
    private Environment environment;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> generalException(Exception exception) {
        logger.error("Unhandled Exception: ", exception); // Log full stack trace

        String msg = (exception.getMessage() != null && !exception.getMessage().isEmpty())
                ? exception.getMessage()
                : "An unexpected error occurred";

        ErrorInfo error = new ErrorInfo(
                msg,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JobPortalException.class)
    public ResponseEntity<ErrorInfo> jobPortalException(JobPortalException exception) {
        logger.error("JobPortalException occurred: ", exception);

        String msg = environment.getProperty(exception.getMessage());
        if (msg == null || msg.isEmpty()) {
            msg = exception.getMessage() != null ? exception.getMessage() : "Internal application error";
        }

        ErrorInfo error = new ErrorInfo(
                msg,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorInfo> validatorExceptionHandler(Exception exception) {
        String msg;

        if (exception instanceof MethodArgumentNotValidException manvException) {
            msg = manvException.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
        } else {
            ConstraintViolationException cvException = (ConstraintViolationException) exception;
            msg = cvException.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
        }

        logger.warn("Validation exception: {}", msg);

        ErrorInfo error = new ErrorInfo(
                msg,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
