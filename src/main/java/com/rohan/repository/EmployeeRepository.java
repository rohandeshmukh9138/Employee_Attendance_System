package com.rohan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohan.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
