package com.rohan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohan.entity.Form16Entity;

public interface Form16Repository extends JpaRepository<Form16Entity, Long>

{
  Long countByStatus(String status);
}
