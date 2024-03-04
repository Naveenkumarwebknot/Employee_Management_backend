package com.example.employee_management.crud.controller;

import com.example.employee_management.crud.model.Employee;
import com.example.employee_management.crud.model.Timesheet;
import com.example.employee_management.crud.service.TimeSheetService;
import error.TimesheetElementNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timesheet")
public class TimeSheetController {

    @Autowired
    private TimeSheetService timesheetservice;
    @GetMapping
    public List<Timesheet> getAlltimesheet() {
        return timesheetservice.getAllTimeSheet();
    }

    @PostMapping
    public Timesheet createtimesheet(@RequestBody Timesheet timesheet) {
        return timesheetservice.createTimesheet(timesheet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Timesheet> gettimesheetById(@PathVariable Long id) throws TimesheetElementNotFoundException {
        Timesheet timesheet = timesheetservice.getTimeSheetById(id);
        return ResponseEntity.ok(timesheet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Timesheet> updatetimesheet(@PathVariable Long id, @RequestBody Timesheet timesheetDetails) throws TimesheetElementNotFoundException {
        Timesheet updatedtimesheet = timesheetservice.updateTimesheet(id, timesheetDetails);
        return ResponseEntity.ok(updatedtimesheet);
    }

    @GetMapping("/time/name/{field}")
    private ResponseEntity<List<Timesheet>> getTimesheetWithSort(@PathVariable String field) {
        List<Timesheet> allProducts = timesheetservice.findTimesheetWithSorting(field);
        return  ResponseEntity.ok(allProducts);
    }

    @GetMapping("/time/pagination/{offset}/{pageSize}")
    private ResponseEntity<Page<Timesheet>> getEmployeeWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Timesheet> productsWithPagination = timesheetservice.findTimesheetWithPagination(offset, pageSize);
        return ResponseEntity.ok(productsWithPagination);
    }

    @GetMapping("/time/paginationAndSort/{offset}/{pageSize}/{field}")
    private ResponseEntity<Page<Timesheet>> getProductsWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field) {
        Page<Timesheet> productsWithPagination = timesheetservice.findTimesheetWithPaginationAndSorting(offset, pageSize, field);
        return ResponseEntity.ok(productsWithPagination);
    }
}
