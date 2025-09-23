	package com.rohan.controller;

import java.io.FileNotFoundException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rohan.service.Form16Service;

import net.sf.jasperreports.engine.JRException;

@RestController
public class Form16Controller {

	@Autowired
	Form16Service service;
	
	
	@GetMapping("/getstatus")
	public Map<String, Long> getStatusCount()
	{
		Map<String, Long> getcount = service.getcount();
		
		return getcount;
		
	}
	
	@GetMapping("/generateReport/{format}")
	public String generateReports(@PathVariable String format) throws FileNotFoundException, JRException
	{
		 String exportReport = service.exportReport(format);
		 return exportReport;
	}
	
	@GetMapping("/generateReportbyId/{id}/{format}")
	public String generateReportsbyId(@PathVariable String format,Long id) throws FileNotFoundException, JRException
	{
		 String exportReport = service.exportReportbyId(format,id);
		 return exportReport;
	}
	
	@GetMapping("/exportrepbyname/{format}/{companyName}")
	public String exportReportByName(@PathVariable String format,@PathVariable String companyName) throws FileNotFoundException, JRException
	{
		String exportReportByName = service.exportReportByName(format,companyName);
		return exportReportByName;
	}
	
	String name;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
