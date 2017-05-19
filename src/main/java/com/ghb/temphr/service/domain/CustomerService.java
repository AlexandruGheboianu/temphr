package com.ghb.temphr.service.domain;

import com.ghb.temphr.api.apimodel.create.CustomerAdd;
import com.ghb.temphr.api.apimodel.list.CustomerListModel;
import com.ghb.temphr.api.apimodel.list.ProjectListModel;
import com.ghb.temphr.api.apimodel.list.SkillEmployeeListModel;
import com.ghb.temphr.api.apimodel.update.CustomerUpdate;
import com.ghb.temphr.service.domain.model.Customer;
import com.ghb.temphr.service.domain.model.Project;
import com.ghb.temphr.service.domain.repository.CustomerRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;


/**
 * Created by agheboianu on 03.03.2017.
 */
@Service
public class CustomerService {

  private static final Hashids hashids = new Hashids("k7ds8kxomx");
  @Autowired
  private CustomerRepository customerRepository;

  public void addCustomer(CustomerAdd customerAdd) {
    Customer customer = new Customer();
    customer.setName(customerAdd.getName());
    customer.setVat(customerAdd.getVat());
    customer.setAddress(customerAdd.getAddress());
    customer.setCity(customerAdd.getCity());
    customer.setCountry(customerAdd.getCountry());
    customer.setPhone(customerAdd.getPhone());
    customer.setEmail(customerAdd.getEmail());
    customer.setContactPerson(customerAdd.getContactPerson());
    customer.setBankAccount(customerAdd.getBankAccount());
    customer.setBankName(customerAdd.getBankName());
    customerRepository.save(customer);
  }

  //added by alin
  public void updateCustomer(CustomerUpdate customerUpdate, String id) {
    Customer customer = customerRepository.findOne(hashids.decode(id)[0]);
    if (customerUpdate.getName() != null) {
      customer.setName(customerUpdate.getName());
    }
    if (customerUpdate.getVat() != null) {
      customer.setVat(customerUpdate.getVat());
    }
    if (customerUpdate.getAddress() != null) {
      customer.setAddress(customerUpdate.getAddress());
    }
    if (customerUpdate.getCity() != null) {
      customer.setCity(customerUpdate.getCity());
    }
    if (customerUpdate.getCountry() != null) {
      customer.setCountry(customerUpdate.getCountry());
    }
    if (customerUpdate.getPhone() != null) {
      customer.setPhone(customerUpdate.getPhone());
    }
    if (customerUpdate.getEmail() != null) {
      customer.setEmail(customerUpdate.getEmail());
    }
    if (customerUpdate.getContactPerson() != null) {
      customer.setContactPerson(customerUpdate.getContactPerson());
    }
    if (customerUpdate.getBankAccount() != null) {
      customer.setBankAccount(customerUpdate.getBankAccount());
    }
    if (customerUpdate.getBankName() != null) {
      customer.setBankName(customerUpdate.getBankName());
    }
    customerRepository.save(customer);
  }
  //end

  public Page<CustomerListModel> listCustomers(Pageable pageable) {
    return customerRepository.findAll(pageable).map(this::getCustomerListModel);
  }

  public Optional<CustomerListModel> getCustomer(String id) {
    long[] decodedIds = hashids.decode(id);
    if (decodedIds.length == 0) {
      return Optional.empty();
    }

    Customer customer = customerRepository.findOne(decodedIds[0]);

    if (customer == null) {
      return Optional.empty();
    }

    return Optional.of(getCustomerListModel(customer));
  }

  private CustomerListModel getCustomerListModel(Customer customer) {
    CustomerListModel customerListModel = new CustomerListModel();
    customerListModel.setId(hashids.encode(customer.getId()));
    customerListModel.setName(customer.getName());
    customerListModel.setVat(customer.getVat());
    customerListModel.setAddress(customer.getAddress());
    customerListModel.setCity(customer.getCity());
    customerListModel.setCountry(customer.getCountry());
    customerListModel.setPhone(customer.getPhone());
    customerListModel.setEmail(customer.getEmail());
    customerListModel.setContactPerson(customer.getContactPerson());
    customerListModel.setBankAccount(customer.getBankAccount());
    customerListModel.setBankName(customer.getBankName());
    return customerListModel;
  }


  public void removeCustomer(String id) {
    long[] decodedIds = hashids.decode(id);

    if (decodedIds.length > 0) {
      Customer customer = customerRepository.findOne(hashids.decode(id)[0]);
      customer.setDeleted(true);
      customerRepository.save(customer);
    }
  }


  /*public Optional<List<ProjectListModel>> getCustomerProjects(String id) {
    Customer customer = customerRepository.findOne();
    customer.getProjects();
    return null;
  }*/
  @Transactional
  public Optional<List<ProjectListModel>> getCustomerProjects(String id) {
    long[] decodedIds = hashids.decode(id);

    if (decodedIds.length == 0) {
      return Optional.empty();
    }

    if (!customerRepository.exists(decodedIds[0])) {
      return Optional.empty();
    }
    //return Optional.of(customerRepository.findOne(decodedIds[0])
    return Optional.of(customerRepository.findOne(hashids.decode(id)[0]).getProjects()
            .stream()
            .map(this::getProjectListModel)
            .collect(Collectors.toList()));
  }

  private ProjectListModel getProjectListModel(Project project) {
    ProjectListModel projectListModel = new ProjectListModel();
    projectListModel.setId(hashids.encode(project.getId()));
    projectListModel.setName(project.getName());
    projectListModel.setStartDate(project.getStartDate());
    return projectListModel;
  }
}
