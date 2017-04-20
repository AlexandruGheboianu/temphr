package com.ghb.temphr.service.domain.repository;

import com.ghb.temphr.service.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by agheboianu on 03.03.2017.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
