package com.aop.SpringAOP.service;

import com.aop.SpringAOP.exception.ResourceNotFoundException;
import com.aop.SpringAOP.model.Employee;
import com.aop.SpringAOP.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        return  employeeRepository.findAll();
    }

    public Optional <Employee> getEmployeeById(int employeeId) throws ResourceNotFoundException{
        return employeeRepository.findById(employeeId);
    }

    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(int employeeId,Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
employee.setEmailId(employeeDetails.getEmailId());
employee.setLastName(employeeDetails.getLastName());
employee.setFirstName(employeeDetails.getFirstName());
final Employee updatedEmployee = employeeRepository.save(employee);
return updatedEmployee;
    }

    public Map<String,Boolean> deleteEmployee(int employeeId) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        employeeRepository.delete(employee);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }

}
