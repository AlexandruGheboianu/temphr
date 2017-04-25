package com.ghb.temphr.api.exception;

/**
 * Created by agheboianu on 25.04.2017.
 */
public class ParentResourceNotFoundException extends RuntimeException {
  public ParentResourceNotFoundException(String message) {
    super(message);
  }
}
