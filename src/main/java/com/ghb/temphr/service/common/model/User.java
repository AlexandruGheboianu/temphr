package com.ghb.temphr.service.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "APP_USER")
@Getter
@NoArgsConstructor
public class User extends BaseEntity {
  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @OneToMany
  @JoinColumn(name = "APP_USER_ID", referencedColumnName = "ID")
  private List<UserRole> roles;

}
