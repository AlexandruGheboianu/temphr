package com.ghb.temphr.api.apimodel.list;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private Date startDate;
}
