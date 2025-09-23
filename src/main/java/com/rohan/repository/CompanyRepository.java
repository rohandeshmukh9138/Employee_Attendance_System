package com.rohan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohan.entity.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long>{

	CompanyEntity findByName(String name);
}
