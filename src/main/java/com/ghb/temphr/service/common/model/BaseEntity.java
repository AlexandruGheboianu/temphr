package com.ghb.temphr.service.common.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * Created by agheboianu on 03.03.2017.
 */

@SuppressFBWarnings( {"EI_EXPOSE_REP"})
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Version
  private int version;

  @CreatedDate
  private Date createdDate;

  @LastModifiedDate
  private Date lastUpdateDate;

  @CreatedBy
  private String createdBy;

  @LastModifiedBy
  private String updatedBy;
}
