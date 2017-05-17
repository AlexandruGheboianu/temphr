package com.ghb.temphr.api.apimodel.update;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
//import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Created by alindobai on 5/17/17.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUpdate {

  @JsonProperty
  @Size(max = 100, min = 1)
  private String name;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private Date startDate;
}