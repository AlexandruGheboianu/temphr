package com.ghb.temphr.security.model.token;

import java.io.Serializable;

@FunctionalInterface
public interface JwtToken extends Serializable{
  String getToken();
}
