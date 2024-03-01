package com.example.employee_management.crud.controller;

import com.example.employee_management.crud.model.Employee;
import com.example.employee_management.crud.service.EmployeeService;
import error.EmloyeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class EmployeeController {
    @Autowired
  private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllProducts() {
        return employeeService.getAllEmployee();
    }

    @PostMapping
    public Employee createProduct(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) throws EmloyeeNotFoundException {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateProduct(@PathVariable Long id, @RequestBody Employee employeeDetails) throws EmloyeeNotFoundException {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id) throws EmloyeeNotFoundException {
        employeeService.deleteEmployee(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{field}")
    private ResponseEntity<List<Employee>> getEmployeeWithSort(@PathVariable String field) {
        List<Employee> allProducts = employeeService.findEmployeeWithSorting(field);
        return  ResponseEntity.ok(allProducts);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    private ResponseEntity<Page<Employee>> getEmployeeWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Employee> productsWithPagination = employeeService.findEmployeeWithPagination(offset, pageSize);
        return ResponseEntity.ok(productsWithPagination);
    }

    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    private ResponseEntity<Page<Employee>> getProductsWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field) {
        Page<Employee> productsWithPagination = employeeService.findEmployeeWithPaginationAndSorting(offset, pageSize, field);
        return ResponseEntity.ok(productsWithPagination);
    }

}
