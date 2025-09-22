package com.rohan.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rohan.entity.AttendanceEntity;
import com.rohan.entity.EmployeeEntity;
import com.rohan.repository.AttendanceRepository;
import com.rohan.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AttendanceService {

    @Autowired
    EmployeeRepository emprepo;

    @Autowired
    AttendanceRepository attrepo;

    public AttendanceEntity clockIn(Long id) {
        EmployeeEntity emp = emprepo.findById(id).orElseThrow();
        AttendanceEntity att = new AttendanceEntity();
        att.setEmployee(emp);
        att.setClockInTime(LocalDateTime.now());
        att.setAction("IN");
        return attrepo.save(att);
    }

    public AttendanceEntity clockOut(Long id) {
        EmployeeEntity emp = emprepo.findById(id).orElseThrow();
        AttendanceEntity att = attrepo.findTopByEmployeeIdAndClockOutTimeIsNullOrderByClockInTimeDesc(id);
        if (att != null) {
            att.setClockOutTime(LocalDateTime.now());
            att.setAction("OUT");
            return attrepo.save(att);
        }
        return null;
    }

    
    
    
    
    
    
    
    public List<AttendanceEntity> getAttendanceWithEffectiveTime() {
        List<AttendanceEntity> records = attrepo.findAll();
        LocalDateTime lastInTime = null;

        for (AttendanceEntity record : records) {
            if ("IN".equals(record.getAction())) {
                lastInTime = record.getClockInTime();
                record.setEffectiveTime("N/A");
            } 
            else if ("OUT".equals(record.getAction()) && lastInTime != null) {
                Duration total = Duration.between(lastInTime, record.getClockOutTime());
                long hours = total.toHours();
                long minutes = total.toMinutes() % 60;
                record.setEffectiveTime(hours + " hrs " + minutes + " min");
                lastInTime = null;  // Reset after calculation
            } 
            else {
                record.setEffectiveTime("N/A");
            }
        }
        return records;
    }

    
    
    
    
    
    
    
    public List<EmployeeEntity> getAllEmployees() {
        return emprepo.findAll();
    }
}
