package com.ghb.temphr.api;

import com.ghb.temphr.api.apimodel.create.EmployeeAdd;
import com.ghb.temphr.api.apimodel.list.EmployeeListModel;
import com.ghb.temphr.api.apimodel.list.EmployeeSkillListModel;
import com.ghb.temphr.api.exception.ParentResourceNotFoundException;
import com.ghb.temphr.api.exception.ResourceNotFoundException;
import com.ghb.temphr.service.business.EmployeeExperienceService;
import com.ghb.temphr.service.domain.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Created by agheboianu on 02.03.2017.
 */

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private EmployeeExperienceService employeeExperienceService;

  @RequestMapping(method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_PREMIUM_MEMBER','ROLE_ADMIN')")
  public Page<EmployeeListModel> employees(Pageable pageable) {
    return employeeService.listEmployees(pageable);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  @PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_PREMIUM_MEMBER','ROLE_ADMIN')")
  public EmployeeListModel employee(@PathVariable String id) {
    Optional<EmployeeListModel> employee = employeeService.getEmployee(id);

    if (!employee.isPresent()) {
      throw new ResourceNotFoundException("No employee with id " + id + " was found.");
    }

    return employee.get();
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}/skills")
  @PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_PREMIUM_MEMBER','ROLE_ADMIN')")
  public List<EmployeeSkillListModel> employeeSkills(@PathVariable String id) {
    Optional<List<EmployeeSkillListModel>> employeeSkills = employeeExperienceService.getEmployeeSkills(id);

    if (!employeeSkills.isPresent()) {
      throw new ParentResourceNotFoundException("/api/employees/" + id);
    }

    return employeeSkills.get();
  }

  @RequestMapping(method = RequestMethod.POST)
  @PreAuthorize("hasAnyRole('ROLE_PREMIUM_MEMBER','ROLE_ADMIN')")
  public ResponseEntity addEmployee(@RequestBody @Validated EmployeeAdd employeeAdd) {
    employeeService.addEmployee(employeeAdd);
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity deleteEmployee(@PathVariable String id) {
    employeeService.removeEmployee(id);
    return new ResponseEntity(HttpStatus.ACCEPTED);
  }

}
