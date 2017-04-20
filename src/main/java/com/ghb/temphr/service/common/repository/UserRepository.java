package com.ghb.temphr.service.common.repository;

import com.ghb.temphr.service.common.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by agheboianu on 20.03.2017.
 */
public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByUsername(String username);
}
