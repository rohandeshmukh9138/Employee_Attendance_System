package com.rohan.controller;

import com.rohan.EmployeeAttendanceSystemApplication;
import com.rohan.entity.AttendanceEntity;
import com.rohan.entity.EmployeeEntity;
import com.rohan.service.AttendanceService;
import com.rohan.repository.EmployeeRepository;
import com.rohan.repository.AttendanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AttendanceController {

    private final EmployeeAttendanceSystemApplication employeeAttendanceSystemApplication;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private EmployeeRepository emprepo;

    @Autowired
    private AttendanceRepository attrepo;

    AttendanceController(EmployeeAttendanceSystemApplication employeeAttendanceSystemApplication) {
        this.employeeAttendanceSystemApplication = employeeAttendanceSystemApplication;
    }

    // Show Punch In / Out form
    @GetMapping("/showform")
    public String showAttendanceForm(Model model) {
        model.addAttribute("employees", attendanceService.getAllEmployees());
        return "attendanceForm";
    }

    // Punch In
    @PostMapping("/clockIn")
    @ResponseBody
    public String clockIn(@RequestParam Long id) {
        AttendanceEntity attendance = attendanceService.clockIn(id);
        return "Clock In recorded at " + attendance.getClockInTime();
    }

    // Punch Out
    @PostMapping("/clockOut")
    @ResponseBody
    public String clockOut(@RequestParam Long id) {
        AttendanceEntity attendance = attendanceService.clockOut(id);
        if (attendance == null) {
            return "Error: Please clock in first!";
        }
        return "Clock Out recorded at " + attendance.getClockOutTime();
    }

    // Show Add Employee Form
    @GetMapping("/addEmployee")
    public String showAddEmployeeForm() {
    	
    	
        return "addEmployee";
    }

    // Save Employee
    @PostMapping("/saveEmployee")
    public String saveEmployee(@RequestParam String name,
                               @RequestParam String department,
                               @RequestParam double salary) {
    	
        EmployeeEntity emp = new EmployeeEntity();
        emp.setName(name);
        emp.setDepartment(department);
        emp.setSalary(salary);
        emprepo.save(emp);
        return "redirect:/showform";
    }

    // View Attendance Table
    @GetMapping("/viewAttendance")
    public String viewAttendance(Model model) {
    	
        List<AttendanceEntity> attendanceList = attendanceService.getAttendanceWithEffectiveTime();
        model.addAttribute("attendanceList", attendanceList);
        return "viewAttendance";
    }
    
    
    
   
}
