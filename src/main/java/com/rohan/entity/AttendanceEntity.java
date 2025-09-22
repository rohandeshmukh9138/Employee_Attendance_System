package com.rohan.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AttendanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    private LocalDateTime clockInTime;
    private LocalDateTime clockOutTime;

    private String action;  // "IN" or "OUT"
    
    @Transient
    private String effectiveTime;  // For showing on UI, not in DB
}
