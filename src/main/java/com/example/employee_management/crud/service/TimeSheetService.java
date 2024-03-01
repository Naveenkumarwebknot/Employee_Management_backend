package com.example.employee_management.crud.service;

import com.example.employee_management.crud.model.Employee;
import com.example.employee_management.crud.model.Timesheet;
import com.example.employee_management.crud.repository.TimeSheetRepository;
import error.TimesheetElementNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeSheetService {
    @Autowired
    private TimeSheetRepository timeSheetRepository;
    @Cacheable(value = "timesheet")
    public List<Timesheet> getAllTimeSheet(){
        return timeSheetRepository.findAll();
    }

    @Cacheable(value = "timesheet", key = "#id")
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

    @Caching(
            evict = {@CacheEvict(value = "timesheet", allEntries = true)},
            put = {@CachePut(value = "timesheet", key = "#Blog.id")}
    )
    public Timesheet updateTimesheet(Long id, Timesheet TimeSheetDetails) throws TimesheetElementNotFoundException {
        Timesheet timesheet = getTimeSheetById(id);
        timesheet.setEmployeeName(TimeSheetDetails.getEmployeeName());
        timesheet.setProjectName(TimeSheetDetails.getProjectName());
        timesheet.setDescription(TimeSheetDetails.getDescription());
        timesheet.setHours(TimeSheetDetails.getHours());
        return timeSheetRepository.save(timesheet);
    }

    public List<Timesheet> findTimesheetWithSorting(String field){
        return  timeSheetRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }


    public Page<Timesheet> findTimesheetWithPagination(int offset, int pageSize){
        Page<Timesheet> timesheet = timeSheetRepository.findAll(PageRequest.of(offset, pageSize));
        return  timesheet;
    }

    public Page<Timesheet> findTimesheetWithPaginationAndSorting(int offset,int pageSize,String field){
        Page<Timesheet> timesheet = timeSheetRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return  timesheet;
    }
}
