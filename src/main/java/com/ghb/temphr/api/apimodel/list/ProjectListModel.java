package com.ghb.temphr.api.apimodel.list;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by alexg on 3/8/2017.
 */

@Getter
@Setter
@NoArgsConstructor
public class ProjectListModel {

  private String id;

  private String name;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private Date startDate;
}
