package com.ghb.temphr.security.model.token;

import com.ghb.temphr.security.config.JwtSettings;
import com.ghb.temphr.security.model.Scopes;
import com.ghb.temphr.security.model.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Factory class that should be always used to create {@link JwtToken}.
 */
@Component
public class JwtTokenFactory {
  private final JwtSettings settings;

  @Autowired
  public JwtTokenFactory(JwtSettings settings) {
    this.settings = settings;
  }

  /**
   * Factory method for issuing new JWT Tokens.
   *
   * @return AccessJwtToken
   */
  public AccessJwtToken createAccessJwtToken(UserContext userContext) {
    if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty()) {
      throw new IllegalArgumentException("User doesn't have any privileges");
    }

    Claims claims = Jwts.claims().setSubject(userContext.getUsername());
    claims.put("scopes", userContext.getAuthorities().stream().map(Object::toString).collect(Collectors.toList()));

    DateTime currentTime = new DateTime();

    String token = Jwts.builder()
        .setClaims(claims)
        .setIssuer(settings.getTokenIssuer())
        .setIssuedAt(currentTime.toDate())
        .setExpiration(currentTime.plusMinutes(settings.getTokenExpirationTime()).toDate())
        .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
        .compact();

    return new AccessJwtToken(token, claims);
  }

  public JwtToken createRefreshToken(UserContext userContext) {
    if (StringUtils.isBlank(userContext.getUsername())) {
      throw new IllegalArgumentException("Cannot create JWT Token without username");
    }

    DateTime currentTime = new DateTime();

    Claims claims = Jwts.claims().setSubject(userContext.getUsername());
    claims.put("scopes", Collections.singletonList(Scopes.REFRESH_TOKEN.authority()));

    String token = Jwts.builder()
        .setClaims(claims)
        .setIssuer(settings.getTokenIssuer())
        .setId(UUID.randomUUID().toString())
        .setIssuedAt(currentTime.toDate())
        .setExpiration(currentTime.plusMinutes(settings.getRefreshTokenExpTime()).toDate())
        .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
        .compact();

    return new AccessJwtToken(token, claims);
  }
}
