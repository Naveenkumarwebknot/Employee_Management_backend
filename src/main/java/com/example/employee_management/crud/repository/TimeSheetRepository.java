package com.example.employee_management.crud.repository;

import com.example.employee_management.crud.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSheetRepository  extends JpaRepository<Timesheet, Long> {

}
