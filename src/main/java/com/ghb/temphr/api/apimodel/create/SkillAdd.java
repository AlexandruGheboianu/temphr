package com.ghb.temphr.api.apimodel.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghb.temphr.api.apimodel.validators.EmployeeExists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkillAdd {

  @NotNull
  @JsonProperty
  @Size(max = 50, min = 1)
  private String name;
  @NotNull
  @JsonProperty
  @Size(max = 50, min = 1)
  private String skillType;
}