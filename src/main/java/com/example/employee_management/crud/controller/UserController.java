package com.example.employee_management.crud.controller;

import com.example.employee_management.crud.model.Timesheet;
import com.example.employee_management.crud.model.UserApp;
import com.example.employee_management.crud.service.UserService;
import error.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/list-user")
    public ResponseEntity<List<UserApp>> fetchUserList() {
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatusCode.valueOf(200));
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<UserApp> fetchUserById(@PathVariable Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.fetchUserById(id), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/create-user")
    public ResponseEntity<UserApp> saveUser(@RequestBody UserApp user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatusCode.valueOf(200));
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<UserApp> updateUser(@PathVariable("id") Long Id, @RequestBody UserApp user) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateUser(Id,user), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws UserNotFoundException{
        return new ResponseEntity<String>(userService.deleteUser(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/name/{field}")
    private ResponseEntity<List<UserApp>> getTimesheetWithSort(@PathVariable String field) {
        List<UserApp> allProducts = userService.findUserWithSorting(field);
        return  ResponseEntity.ok(allProducts);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    private ResponseEntity<Page<UserApp>> getEmployeeWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<UserApp> productsWithPagination = userService.findUserWithPagination(offset, pageSize);
        return ResponseEntity.ok(productsWithPagination);
    }

    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    private ResponseEntity<Page<UserApp>> getProductsWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field) {
        Page<UserApp> productsWithPagination = userService.findUserWithPaginationAndSorting(offset, pageSize, field);
        return ResponseEntity.ok(productsWithPagination);
    }

}