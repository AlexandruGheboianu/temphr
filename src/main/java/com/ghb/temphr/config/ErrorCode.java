package com.ghb.temphr.config;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumeration of REST Error types.
 */
public enum ErrorCode {
  GLOBAL(2),

  AUTHENTICATION(10), JWT_TOKEN_EXPIRED(11);

  private int code;

  private ErrorCode(int code) {
    this.code = code;
  }

  @JsonValue
  public int getCode() {
    return code;
  }
}
