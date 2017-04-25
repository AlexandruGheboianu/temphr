package com.ghb.temphr.security.config;

import com.ghb.temphr.security.model.token.JwtToken;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "demo.security.jwt")
@Getter
@Setter
public class JwtSettings {
  /**
   * {@link JwtToken} will expire after this time.
   */
  private Integer tokenExpirationTime;

  /**
   * Token issuer.
   */
  private String tokenIssuer;

  /**
   * Key is used to sign {@link JwtToken}.
   */
  private String tokenSigningKey;

  /**
   * {@link JwtToken} can be refreshed during this timeframe.
   */
  private Integer refreshTokenExpTime;

}
