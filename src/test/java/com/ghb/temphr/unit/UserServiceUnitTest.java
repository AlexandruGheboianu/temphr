package com.ghb.temphr.unit;

import com.ghb.temphr.service.common.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Created by agheboianu on 02.05.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceUnitTest {
  @Autowired
  private UserService userService;


  @Test
  public void userNotFound() {
    assertEquals(Optional.empty(), userService.findByUserName("missing"));
  }
}
