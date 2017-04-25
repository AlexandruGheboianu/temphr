package com.ghb.temphr.api.apimodel.validators;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * Created by Ghebo on 1/19/2016.
 */
@Target( {ElementType.METHOD, ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = EmployeeExistsValidator.class)
@Documented
public @interface EmployeeExists {
  String message();

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};


  boolean exists();
}