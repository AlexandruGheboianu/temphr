package com.ghb.temphr.api.apimodel.update;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghb.temphr.api.apimodel.validators.EmployeeExists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;

/**
 * Created by alindobai on 5/17/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdate {

  @JsonProperty
  @Size(max = 50, min = 1)
  private String name;

  @JsonProperty
  @Size(max = 50, min = 1)
  private String vat;

  @JsonProperty
  @Size(max = 50, min = 1)
  private String address;

  @JsonProperty
  @Size(max = 50, min = 1)
  private String city;

  @JsonProperty
  @Size(max = 50, min = 1)
  private String country;

  @JsonProperty
  @Size(max = 50, min = 1)
  private String phone;

  @Email
  @JsonProperty
  @Size(max = 50, min = 1)
  @EmployeeExists(message = "Email address in use.", exists = false)
  private String email;

  @JsonProperty
  @Size(max = 50, min = 1)
  private String contactPerson;

  @JsonProperty
  @Size(max = 50, min = 1)
  private String bankAccount;

  @JsonProperty
  @Size(max = 50, min = 1)
  private String bankName;
}



