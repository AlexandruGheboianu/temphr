package com.ghb.temphr.api;

import com.ghb.temphr.api.apimodel.create.CustomerAdd;
import com.ghb.temphr.api.apimodel.list.CustomerListModel;
//import com.ghb.temphr.api.apimodel.list.CustomerSkillListModel;
import com.ghb.temphr.api.apimodel.list.ProjectListModel;
import com.ghb.temphr.api.apimodel.update.CustomerUpdate;
import com.ghb.temphr.api.exception.ParentResourceNotFoundException;
import com.ghb.temphr.api.exception.ResourceNotFoundException;
import com.ghb.temphr.service.business.EmployeeExperienceService;
import com.ghb.temphr.service.domain.CustomerService;
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
@RequestMapping("/api/customers")
public class CustomerController {

  @Autowired
  private CustomerService customerService;


  @RequestMapping(method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_PREMIUM_MEMBER','ROLE_ADMIN')")
  public Page<CustomerListModel> customers(Pageable pageable) {
    return customerService.listCustomers(pageable);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  @PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_PREMIUM_MEMBER','ROLE_ADMIN')")
  public CustomerListModel customer(@PathVariable String id) {
    Optional<CustomerListModel> customer = customerService.getCustomer(id);

    if (!customer.isPresent()) {
      throw new ResourceNotFoundException("No customer with id " + id + " was found.");
    }

    return customer.get();
  }


  @RequestMapping(method = RequestMethod.GET, value = "/{id}/projects")
  @PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_PREMIUM_MEMBER','ROLE_ADMIN')")
  public List<ProjectListModel> customerProjects(@PathVariable String id) {
    Optional<List<ProjectListModel>> customerProjects = customerService.getCustomerProjects(id);

    if (!customerProjects.isPresent()) {
      throw new ParentResourceNotFoundException("/api/customers/" + id);
    }

    return customerProjects.get();
  }


  @RequestMapping(method = RequestMethod.POST)
  @PreAuthorize("hasAnyRole('ROLE_PREMIUM_MEMBER','ROLE_ADMIN')")
  public ResponseEntity addCustomer(@RequestBody @Validated CustomerAdd customerAdd) {
    customerService.addCustomer(customerAdd);
    return new ResponseEntity(HttpStatus.CREATED);
  }

  //added by alin
  @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
  @PreAuthorize("hasAnyRole('ROLE_PREMIUM_MEMBER','ROLE_ADMIN')")
  public ResponseEntity updateCustomer(@RequestBody @Validated CustomerUpdate customerUpdate, @PathVariable String id) {
    customerService.updateCustomer(customerUpdate, id);
    return new ResponseEntity(HttpStatus.OK);
  }
  //end

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity deleteCustomer(@PathVariable String id) {
    customerService.removeCustomer(id);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

}
