package com.ghb.temphr.service.domain.repository;

import com.ghb.temphr.service.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by agheboianu on 03.03.2017.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  Customer findOneByName(String name);

  Customer findOneByEmail(String email);
}
