package com.ghb.temphr.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghb.temphr.api.exception.ParentResourceNotFoundException;
import com.ghb.temphr.api.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by agheboianu on 21.03.2017.
 */
@ControllerAdvice
public class RestControllerAdvice {
  private static final Logger logger = LoggerFactory.getLogger(RestControllerAdvice.class);

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception) {
    return createResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ParentResourceNotFoundException.class)
  public ResponseEntity<Object> handleParentResourceNotFoundException(ParentResourceNotFoundException exception) {
    Map<String, String> message = new HashMap<>();
    message.put("type", "ParentNotFound");
    message.put("description", "The parent resource was not found.");
    message.put("parent_uri", exception.getMessage());
    try {
      return createJsonResponseEntity(new ObjectMapper().writeValueAsString(message), HttpStatus.NOT_FOUND);
    } catch (JsonProcessingException excep) {
      logger.debug("Exception while creating Json exception message.", excep);
      return createResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGeneralException(Exception exception) {
    return createResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleValidationException(Exception exception) {
    return createResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler( {AccessDeniedException.class, InsufficientAuthenticationException.class})
  public ResponseEntity<Object> handleAccessDeniedException(Exception exception, WebRequest request) {
    return createResponseEntity("You are not allowed to access this resource", HttpStatus.FORBIDDEN);
  }


  private ResponseEntity<Object> createResponseEntity(String message, HttpStatus status) {
    return new ResponseEntity<>(message, status);
  }

  private ResponseEntity<Object> createJsonResponseEntity(String message, HttpStatus status) {
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8.toString());
    return new ResponseEntity<>(message, headers, status);
  }
}
