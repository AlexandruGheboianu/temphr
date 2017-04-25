package com.ghb.temphr.security.endpoint;

import com.ghb.temphr.security.auth.jwt.extractor.TokenExtractor;
import com.ghb.temphr.security.config.JwtSettings;
import com.ghb.temphr.security.config.WebSecurityConfig;
import com.ghb.temphr.security.exception.InvalidJwtToken;
import com.ghb.temphr.security.model.UserContext;
import com.ghb.temphr.security.model.token.JwtToken;
import com.ghb.temphr.security.model.token.JwtTokenFactory;
import com.ghb.temphr.security.model.token.RawAccessJwtToken;
import com.ghb.temphr.security.model.token.RefreshToken;
import com.ghb.temphr.service.common.model.User;
import com.ghb.temphr.service.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RefreshTokenEndpoint {
  @Autowired
  private JwtTokenFactory tokenFactory;
  @Autowired
  private JwtSettings jwtSettings;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  @Qualifier("jwtHeaderTokenExtractor")
  private TokenExtractor tokenExtractor;

  @RequestMapping(value = "/api/auth/token", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
  @ResponseBody
  public JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM));

    RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
    RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(InvalidJwtToken::new);

    String subject = refreshToken.getSubject();
    User user = userRepository.findByUsername(subject).orElseThrow(() -> new UsernameNotFoundException("User not found: " + subject));

    if (user.getRoles() == null || user.getRoles().isEmpty()) {
      throw new InsufficientAuthenticationException("User has no roles assigned");
    }
    List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
        .collect(Collectors.toList());

    UserContext userContext = UserContext.create(user.getUsername(), authorities);

    return tokenFactory.createAccessJwtToken(userContext);
  }
}
