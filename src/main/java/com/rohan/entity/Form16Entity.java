package com.rohan.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.processing.Generated;

import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="form_16")
public class Form16Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="company_id")
	private Long companyId;
	
	@Column(name="date")
	private LocalDateTime date;
	
	@Column(name="employee_id")
	private Long employeeId;
	
	@Column(name="financial_year")
	private String financialYear;
	
	@Column(name="last_modified")
	private LocalDateTime lastModified;
	
	@Column(name="last_modified_by")
	private String lastModifiedBy;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="status")
	private String status;
	
	
}
