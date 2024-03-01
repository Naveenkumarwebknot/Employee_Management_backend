package com.example.employee_management.crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Employee {
  @Id
    Long id;
   String name;
   String email;
   String role;
   String phoneNumber;

}
