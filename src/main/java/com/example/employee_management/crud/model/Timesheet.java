package com.example.employee_management.crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Timesheet {
    @Id
    Long id;
    String employeeName;
    String projectName;
    String description;
    String hours;

}
