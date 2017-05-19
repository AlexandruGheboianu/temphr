package com.ghb.temphr.service.domain.model;

import com.ghb.temphr.service.common.model.BaseEntity;
import com.ghb.temphr.service.domain.model.enumerated.SkillType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Created by alindobai on 5/19/17.
 */

@Getter
@Setter
@Entity
@Table(name = "CUSTOMERS")
@Where(clause = "deleted='false'")
public class Customer extends BaseEntity {



  @OneToMany
  @JoinColumn(name = "customer_id")
  private List<Project> projects;


  @Column(length = 50, nullable = false)
  private String name;

  @Column(length = 50, nullable = false)
  private String vat;

  @Column(length = 50, nullable = false)
  private String address;

  @Column(length = 50, nullable = false)
  private String city;

  @Column(length = 50, nullable = false)
  private String country;

  @Column(length = 50, nullable = false)
  private String phone;

  @Column(length = 50, nullable = false)
  private String email;

  //@Enumerated(EnumType.STRING)
  @Column(name = "contact_person")
  private String contactPerson;

  @Column(name = "bank_account")
  private String bankAccount;

  @Column(name = "bank_name")
  private String bankName;

  private boolean deleted;
}
