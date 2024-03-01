package com.example.employee_management.crud.repository;

import com.example.employee_management.crud.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {

}
