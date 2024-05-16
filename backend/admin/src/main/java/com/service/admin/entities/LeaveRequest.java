package com.service.admin.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest {
    private int LeaveRequestId;
    private int studentID;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private RequestStatus status;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String approvedBy;

    
}

