package com.ghb.temphr.security.auth.ajax;

import com.ghb.temphr.security.model.UserContext;
import com.ghb.temphr.service.common.UserService;
import com.ghb.temphr.service.common.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by agheboianu on 02.03.2017.
 */
@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {
  private final BCryptPasswordEncoder encoder;
  private final UserService userService;

  @Autowired
  public AjaxAuthenticationProvider(final UserService userService, final BCryptPasswordEncoder encoder) {
    this.userService = userService;
    this.encoder = encoder;
  }

  @Override
  public Authentication authenticate(Authentication authentication) {
    Assert.notNull(authentication, "No authentication data provided");

    String username = (String) authentication.getPrincipal();
    String password = (String) authentication.getCredentials();

    User user = userService.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

    if (!encoder.matches(password, user.getPassword())) {
      throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
    }

    if (user.getRoles().isEmpty()) {
      throw new InsufficientAuthenticationException("User has no roles assigned");
    }

    List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
        .collect(Collectors.toList());

    UserContext userContext = UserContext.create(user.getUsername(), authorities);

    return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
