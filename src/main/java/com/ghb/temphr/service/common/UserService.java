package com.ghb.temphr.service.common;

import com.ghb.temphr.service.common.model.User;
import com.ghb.temphr.service.common.repository.UserRepository;
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

    @Transactional
    public Optional<User> findByUserName(String userName) {
        Optional<User> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isPresent()) {
            userOptional.get().getRoles().size();
        }
        return userOptional;
    }
}
