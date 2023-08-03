package com.javainterns.bookingroom.controller;

import com.javainterns.bookingroom.exceptions.*;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(NoRecordFoundException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleNoRecordFoundException(NoRecordFoundException exception) {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("timestamp", new Date().toString());
        response.put("status", HttpStatus.NOT_FOUND.toString());
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /*    @ExceptionHandler(StartTimeIsGreaterThanEndTime.class)
        @ResponseBody
        public ResponseEntity<Map<String,String>> stratTiemIsGreaterThanEndTime(StartTimeIsGreaterThanEndTime exception){
            Map<String, String> response = new LinkedHashMap<>();
            response.put("timestamp", new Date().toString());
            response.put("status", HttpStatus.BAD_REQUEST.toString());
            response.put("message", exception.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }*/
    @ExceptionHandler(StartTimeIsGreaterThanEndTime.class)
    @ResponseBody
    public ProblemDetail startTimeIsGreaterThanEndTime(StartTimeIsGreaterThanEndTime exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
        problemDetail.setProperty("timestamp", new Date());
        problemDetail.setTitle("Start time greater than time");
        problemDetail.setType(URI.create(""));
        return problemDetail;
    }

    @ExceptionHandler(RoomAlreadyBooked.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleRoomAlreadyBooked(RoomAlreadyBooked exception) {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("timestamp", new Date().toString());
        response.put("status", HttpStatus.CONFLICT.toString());
        response.put("message", exception.getMessage() + " Room already booked at this time");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HoursOfOperationNotAvailableException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleHoursOfOperationNotAvailableException(HoursOfOperationNotAvailableException exception) {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("timestamp", new Date().toString());
        response.put("status", HttpStatus.CONFLICT.toString());
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("timestamp", new Date().toString());
        response.put("status", HttpStatus.BAD_REQUEST.toString());
        response.put("error", "Argument Not Valid");
        List<String> errors = new java.util.ArrayList<>(ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
        response.put("message", errors.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(UnexpectedTypeException exception) {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("timestamp", new Date().toString());
        response.put("status", HttpStatus.BAD_REQUEST.toString());
        response.put("error", "Argument Not Valid");
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<Map<String, String>> handleInvalidDataException(InvalidDataException exception) {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("timestamp", new Date().toString());
        response.put("status", HttpStatus.BAD_REQUEST.toString());
        response.put("error", "Argument Not Valid");
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
