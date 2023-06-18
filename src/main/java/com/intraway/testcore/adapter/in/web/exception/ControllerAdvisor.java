package com.intraway.testcore.adapter.in.web.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import com.intraway.testcore.domain.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Object> handleNoDataFoundException(
      BadRequestException ex, WebRequest request) {

    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", "400");
    body.put("error", "BadRequest");
    body.put("exception", ex.getClass());
    body.put("message", ex.getMessage());
    body.put("path", ((ServletWebRequest) request).getRequest().getServletPath());

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }
}
