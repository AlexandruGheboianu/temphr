package com.ghb.temphr.security.auth.ajax;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghb.temphr.security.model.UserContext;
import com.ghb.temphr.security.model.token.JwtToken;
import com.ghb.temphr.security.model.token.JwtTokenFactory;
import com.ghb.temphr.service.common.model.User;
import com.ghb.temphr.service.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
 * AjaxAwareAuthenticationSuccessHandler
 */
@Component
public class AjaxAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  private final ObjectMapper mapper;
  private final JwtTokenFactory tokenFactory;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  public AjaxAwareAuthenticationSuccessHandler(final ObjectMapper mapper, final JwtTokenFactory tokenFactory) {
    this.mapper = mapper;
    this.tokenFactory = tokenFactory;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                      Authentication authentication) throws IOException, ServletException {
    UserContext userContext = (UserContext) authentication.getPrincipal();


    JwtToken accessToken = tokenFactory.createAccessJwtToken(userContext);
    JwtToken refreshToken = tokenFactory.createRefreshToken(userContext);

    User user = userRepository.findByUsername(userContext.getUsername()).get();

    Map<String, String> tokenMap = new HashMap<>();
    tokenMap.put("token", accessToken.getToken());
    tokenMap.put("refreshToken", refreshToken.getToken());
    tokenMap.put("firstName", user.getFirstName());
    tokenMap.put("lastName", user.getLastName());
    tokenMap.put("roles", serializeRoles(userContext.getAuthorities()));

    response.setStatus(HttpStatus.OK.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    mapper.writeValue(response.getWriter(), tokenMap);
  }

  private String serializeRoles(List<GrantedAuthority> authorities) {
    return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining("#"));
  }
}
