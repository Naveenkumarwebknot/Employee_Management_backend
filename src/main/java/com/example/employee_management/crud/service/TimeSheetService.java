package com.example.employee_management.crud.service;

import com.example.employee_management.crud.model.Employee;
import com.example.employee_management.crud.model.Timesheet;
import com.example.employee_management.crud.repository.TimeSheetRepository;
import error.TimesheetElementNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeSheetService {
    @Autowired
    private TimeSheetRepository timeSheetRepository;

    public List<Timesheet> getAllTimeSheet(){
        return timeSheetRepository.findAll();
    }


    public Timesheet getTimeSheetById(Long id) throws TimesheetElementNotFoundException {
        Optional<Timesheet> timesheetElementOptional = timeSheetRepository.findById(id);
        if (timesheetElementOptional.isPresent()) {
            return timesheetElementOptional.get();
        } else {
            throw new TimesheetElementNotFoundException("Timesheet element not found with ID: " + id);
        }
    }

    public Timesheet createTimesheet(Timesheet timesheet) {
        return timeSheetRepository.save(timesheet);
    }

    public Timesheet updateTimesheet(Long id, Timesheet TimeSheetDetails) throws TimesheetElementNotFoundException {
        Timesheet timesheet = getTimeSheetById(id);
        timesheet.setEmployeeName(TimeSheetDetails.getEmployeeName());
        timesheet.setProjectName(TimeSheetDetails.getProjectName());
        timesheet.setDescription(TimeSheetDetails.getDescription());
        timesheet.setHours(TimeSheetDetails.getHours());
        return timeSheetRepository.save(timesheet);
    }
}
