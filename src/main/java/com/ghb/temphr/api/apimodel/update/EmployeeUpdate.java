package com.ghb.temphr.api.apimodel.update;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghb.temphr.api.apimodel.validators.EmployeeExists;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by alindobai on 5/17/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdate {

  @JsonProperty
  @Size(max = 50, min = 1)
  private String firstName;

  @JsonProperty
  @Size(max = 50, min = 1)
  private String lastName;

  @Email
  @JsonProperty
  @Size(max = 50, min = 1)
  @EmployeeExists(message = "Email address in use.", exists = false)
  private String email;
}



