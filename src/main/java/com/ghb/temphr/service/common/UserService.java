package com.ghb.temphr.service.common;

import com.ghb.temphr.service.common.model.User;
import com.ghb.temphr.service.common.repository.UserRepository;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by agheboianu on 20.03.2017.
 */
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT")
  @Transactional
  public Optional<User> findByUserName(String userName) {
    Optional<User> userOptional = userRepository.findByUsername(userName);
    if (userOptional.isPresent()) {
      userOptional.get().getRoles().size();
    }
    return userOptional;
  }
}
