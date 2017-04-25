package com.ghb.temphr.api.apimodel.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghb.temphr.api.apimodel.validators.EmployeeExists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by agheboianu on 03.03.2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAdd {

  @NotNull
  @JsonProperty
  @Size(max = 50, min = 1)
  private String firstName;
  @NotNull
  @JsonProperty
  @Size(max = 50, min = 1)
  private String lastName;
  @NotNull
  @Email
  @JsonProperty
  @Size(max = 50, min = 1)
  @EmployeeExists(message = "Email address in use.", exists = false)
  private String email;
}
