package com.rohan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohan.entity.AttendanceEntity;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long>{

    AttendanceEntity findTopByEmployeeIdAndClockOutTimeIsNullOrderByClockInTimeDesc(Long id);

    List<AttendanceEntity> findByEmployeeIdOrderByClockInTimeAsc(Long id);
    
    

}
