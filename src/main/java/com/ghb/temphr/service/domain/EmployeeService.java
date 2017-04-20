package com.ghb.temphr.service.domain;

import com.ghb.temphr.api.apimodel.create.EmployeeAdd;
import com.ghb.temphr.api.apimodel.list.EmployeeListModel;
import com.ghb.temphr.service.domain.model.Employee;
import com.ghb.temphr.service.domain.repository.EmployeeRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by agheboianu on 03.03.2017.
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private static final Hashids hashids = new Hashids("k7ds8kxomx");

    public void addEmployee(EmployeeAdd employeeAdd) {
        Employee employee = new Employee();
        employee.setFirstName(employeeAdd.getFirstName());
        employee.setLastName(employeeAdd.getLastName());
        employee.setEmail(employeeAdd.getEmail());
        employeeRepository.save(employee);
    }

    public Page<EmployeeListModel> listEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable).map(this::getEmployeeListModel);
    }

    public EmployeeListModel getEmployee(String id) {
        return getEmployeeListModel(employeeRepository.findOne(hashids.decode(id)[0]));
    }

    private EmployeeListModel getEmployeeListModel(Employee employee) {
        EmployeeListModel employeeListModel = new EmployeeListModel();
        employeeListModel.setId(hashids.encode(employee.getId()));
        employeeListModel.setFirstName(employee.getFirstName());
        employeeListModel.setLastName(employee.getLastName());
        employeeListModel.setEmail(employee.getEmail());
        return employeeListModel;
    }


}
