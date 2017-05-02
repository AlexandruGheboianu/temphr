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
public class User {
  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @OneToMany
  @JoinColumn(name = "APP_USER_ID", referencedColumnName = "ID")
  private List<UserRole> roles;

}
