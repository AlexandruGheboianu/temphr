package com.ghb.temphr.unit;

import com.ghb.temphr.service.domain.model.Employee;
import com.ghb.temphr.service.domain.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by agheboianu on 27.04.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeUnitTest {
  @Autowired
  private EmployeeRepository employeeRepository;


  @Test
  public void employeeDeleteReflectedInRead() {
    long initialCount = employeeRepository.count();

    Employee employee = new Employee();
    employee.setFirstName("Alex");
    employee.setLastName("Alexander");
    employee.setEmail("aa@example.com");
    employee = employeeRepository.save(employee);

    long newCount = employeeRepository.count();

    assertTrue(newCount - initialCount == 1);

    employee.setDeleted(true);
    employee = employeeRepository.save(employee);

    assertTrue(initialCount == employeeRepository.count());
  }
}
