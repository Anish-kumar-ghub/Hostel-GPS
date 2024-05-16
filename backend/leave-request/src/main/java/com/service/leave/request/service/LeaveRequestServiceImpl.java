package com.service.leave.request.service;

import java.time.LocalDate;
import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.service.leave.request.entities.LeaveRequest;
import com.service.leave.request.repositories.LeaveRequestRepository;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeaveRequestServiceImpl.class);

    @Autowired
    LeaveRequestRepository leaveRequestRepository;

    @Override
    @Transactional
    public int submitRequestForm(int studentId, LocalDate startDate, LocalDate endDate, String reason, String status, LocalDate createdAt) {
        try {
            return leaveRequestRepository.submitRequestForm(studentId, startDate, endDate, reason, status, createdAt);
        } catch (Exception e) {
            LOGGER.error("Failed to submit leave request form: {}", e.getMessage());
            throw new RuntimeException("Failed to submit leave request form", e);
        }
    }

    @Override
    @Transactional
    public int adminApproval(int LeaveRequestId, String status, String approvedBy, LocalDate updatedAt) {
        try {
            return leaveRequestRepository.adminApproval(LeaveRequestId, approvedBy, status, updatedAt);
        } catch (Exception e) {
            LOGGER.error("Failed to update leave request approval status: {}", e.getMessage());
            throw new RuntimeException("Failed to update leave request approval status", e);
        }
    }

    @Override
    public List<LeaveRequest> getAllLeaveRequests(int studentid) {
        try {
            return leaveRequestRepository.getAllLeaveRequests(studentid);
        } catch (Exception e) {
            LOGGER.error("Failed to fetch all leave requests: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch all leave requests", e);
        }
    }

    @Override
    public List<LeaveRequest> getAllApprovedRequests(int studentid) {
        try {
            return leaveRequestRepository.getLeaveRequestsByStatus_Approved(studentid);
        } catch (Exception e) {
            LOGGER.error("Failed to fetch approved leave requests: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch approved leave requests", e);
        }
    }

    @Override
    public List<LeaveRequest> getAllRejectedRequests(int studentid) {
        try {
            return leaveRequestRepository.getLeaveRequestsByStatus_Rejected(studentid);
        } catch (Exception e) {
            LOGGER.error("Failed to fetch rejected leave requests: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch rejected leave requests", e);
        }
    }

    @Override
    public List<LeaveRequest> getAllPendingRequests(int studentid) {
        try {
            return leaveRequestRepository.getLeaveRequestsByStatus_Pending(studentid);
        } catch (Exception e) {
            LOGGER.error("Failed to fetch pending leave requests: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch pending leave requests", e);
        }
    }

    @Override
    public List<LeaveRequest> getPendingRequests() {
        try {
            return leaveRequestRepository.getLeaveRequestsByStatus_Pending();
        } catch (Exception e) {
            LOGGER.error("Failed to fetch all pending leave requests: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch all pending leave requests", e);
        }
    }
}
