package com.ghb.temphr.api.exception;

/**
 * Created by agheboianu on 21.03.2017.
 */
public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
