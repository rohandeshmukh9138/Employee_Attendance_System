package com.rohan.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.rohan.entity.CompanyEntity;
import com.rohan.entity.Form16Entity;
import com.rohan.repository.CompanyRepository;
import com.rohan.repository.Form16Repository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class Form16Service {

	@Autowired
	Form16Repository repo;
	
	@Autowired
	CompanyRepository crepo;
	
	public Map<String, Long> getcount()
	{
		Map<String, Long> map=new HashMap<>();
		map.put("SUCCESS", repo.countByStatus("SUCCESS"));
		map.put("FAILED", repo.countByStatus("FAILED"));
		map.put("PENDING", repo.countByStatus("PENDING"));
		
		return map;
		
	}
	
	
	public String exportReport(String format) throws FileNotFoundException, JRException
	{
	String path="C:\\reports";
	List<Form16Entity> list = repo.findAll();
	File file=ResourceUtils.getFile("classpath:reports\\Blank_A4.jrxml");
	JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());	
	JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(list);
	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null,dataSource);
	
	
	if(format.equalsIgnoreCase("html"))
	{
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Blank_A4.html");
	}
	else if(format.equalsIgnoreCase("pdf"))
	{
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Blank_A4.pdf");
	}
	
	return "Report generated at: " + path;
	}
	
	
	
	
	
	
	public String exportReportbyId(String format,Long id) throws FileNotFoundException, JRException
	{
	String path="C:\\reports";
	Optional<Form16Entity> byId = repo.findById(id);
	File file=ResourceUtils.getFile("classpath:reports\\Employee_details.jrxml");
	JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());	
	JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(Collections.singletonList(byId.get()));
	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null,dataSource);
	
	
	if(format.equalsIgnoreCase("html"))
	{
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Employee_details.html");
	}
	else if(format.equalsIgnoreCase("pdf"))
	{
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Employee_details.pdf");
	}
	
	return "Report generated at: " + path;
	}
	
	
	
	
	
	
	
	public String exportReportByName(String format,String name) throws FileNotFoundException, JRException
	{
		String path="C:\\reports";
		CompanyEntity byCompanyName = crepo.findByName(name);
		System.err.println(byCompanyName);
		File file=ResourceUtils.getFile("classpath:reports\\Blank_A4.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		//converted to list
		List<CompanyEntity> list=new ArrayList<CompanyEntity>();
		list.add(byCompanyName);
		
		JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(list);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null,dataSource);
		
		if(format.equalsIgnoreCase("html"))
		{
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+ "\\Employee_details.html");
		}
		else
		{
			JasperExportManager.exportReportToPdfFile(jasperPrint, path+ "\\Employee_details.pdf");
		}
		
		return "Report generated at"+path;
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
