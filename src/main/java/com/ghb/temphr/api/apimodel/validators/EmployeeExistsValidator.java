package com.ghb.temphr.api.apimodel.validators;

import com.ghb.temphr.service.domain.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Ghebo on 1/19/2016.
 */
public class EmployeeExistsValidator implements ConstraintValidator<EmployeeExists, Object> {
  @Autowired
  private EmployeeRepository employeeRepository;

  private boolean exists;

  @Override
  public void initialize(final EmployeeExists constraintAnnotation) {
    exists = constraintAnnotation.exists();
  }

  @Override
  public boolean isValid(final Object value, final ConstraintValidatorContext context) {
    String email = (String) value;
    return (employeeRepository.findOneByEmail(email) != null) == exists;
  }
}