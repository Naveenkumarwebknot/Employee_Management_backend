package com.example.employee_management.crud.service;

import com.example.employee_management.crud.model.Employee;
import com.example.employee_management.crud.model.Timesheet;
import com.example.employee_management.crud.repository.EmployeeRepository;
import error.TimesheetElementNotFoundException;
import error.EmloyeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) throws EmloyeeNotFoundException {
        Optional<Employee> employeeElementOptional = employeeRepository.findById(id);
        if (employeeElementOptional.isPresent()) {
            return employeeElementOptional.get();
        } else {
            throw new EmloyeeNotFoundException("employee element not found with ID: " + id);
        }

    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) throws EmloyeeNotFoundException {
        Employee employee = getEmployeeById(id);
        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setRole(employeeDetails.getRole());
        employee.setPhoneNumber(employeeDetails.getPhoneNumber());
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) throws EmloyeeNotFoundException {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }
}