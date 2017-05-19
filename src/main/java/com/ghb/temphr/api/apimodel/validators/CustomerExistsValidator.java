package com.ghb.temphr.api.apimodel.validators;

import com.ghb.temphr.service.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Ghebo on 1/19/2016.
 */
public class CustomerExistsValidator implements ConstraintValidator<CustomerExists, Object> {
  @Autowired
  private CustomerRepository customerRepository;

  private boolean exists;

  @Override
  public void initialize(final CustomerExists constraintAnnotation) {
    exists = constraintAnnotation.exists();
  }

  @Override
  public boolean isValid(final Object value, final ConstraintValidatorContext context) {
    String email = (String) value;
    return (customerRepository.findOneByEmail(email) != null) == exists;
  }
}