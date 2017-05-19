package com.ghb.temphr.api.apimodel.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghb.temphr.api.apimodel.validators.CustomerExists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by alindobai on 05.19.2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAdd {

  @NotNull
  @JsonProperty
  @Size(max = 50, min = 1)
  private String name;
  @NotNull
  @JsonProperty
  @Size(max = 50, min = 1)
  private String vat;
  @NotNull
  @JsonProperty
  @Size(max = 50, min = 1)
  private String address;
  @NotNull
  @JsonProperty
  @Size(max = 50, min = 1)
  private String city;
  @NotNull
  @JsonProperty
  @Size(max = 50, min = 1)
  private String country;
  @NotNull
  @JsonProperty
  @Size(max = 50, min = 1)
  private String phone;
  @NotNull
  @Email
  @JsonProperty
  @Size(max = 50, min = 1)
  @CustomerExists(message = "Email address in use.", exists = false)
  private String email;
  @NotNull
  @JsonProperty
  @Size(max = 50, min = 1)
  private String contactPerson;
  @NotNull
  @JsonProperty
  @Size(max = 50, min = 1)
  private String bankAccount;
  @NotNull
  @JsonProperty
  @Size(max = 50, min = 1)
  private String bankName;
}
