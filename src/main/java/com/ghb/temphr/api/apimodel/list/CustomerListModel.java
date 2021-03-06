package com.ghb.temphr.api.apimodel.list;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by agheboianu on 02.03.2017.
 */
@Getter
@Setter
@NoArgsConstructor
public class CustomerListModel {
  private String id;

  private String name;

  private String vat;

  private String address;

  private String city;

  private String country;

  private String phone;

  private String email;

  private String contactPerson;

  private String bankAccount;

  private String bankName;
}
