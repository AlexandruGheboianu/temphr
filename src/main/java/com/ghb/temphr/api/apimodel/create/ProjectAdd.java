package com.ghb.temphr.api.apimodel.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by alexg on 3/8/2017.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAdd {
  @NotNull
  @JsonProperty
  @Size(max = 100, min = 1)
  private String name;

  private String startDate;
}
