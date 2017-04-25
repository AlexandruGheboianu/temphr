package com.ghb.temphr.service.domain.model;

import com.ghb.temphr.service.common.model.BaseEntity;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Created by alexg on 3/8/2017.
 */

@SuppressFBWarnings( {"EI_EXPOSE_REP"})
@Getter
@Setter
@Entity
@Table(name = "PROJECTS")
@Where(clause = "deleted='false'")
public class Project extends BaseEntity {

  private String name;

  private Date startDate;

  private boolean deleted;
}
