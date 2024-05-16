package com.service.admin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.service.admin.entities.Admin;
import com.service.admin.entities.LeaveRequest;
import com.service.admin.repositories.AdminRepository;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired 
    AdminRepository adminRepository;
    
    @Autowired
    LeaveRequestClient leaveRequestClient;


    @Override
    public Admin findByEmailIdAndPassword(String emailId, String password) {
        try {
            return adminRepository.findByEmailIdAndPassword(emailId, password);
        } catch (Exception e) {
            System.out.println("Failed to find admin by email and password: " + e.getMessage());
            return null;
        }
    }


    @Override
    public void adminApproval(int leaveRequestId, String status, String approved_by) {
        try {
            ResponseEntity<String> response = leaveRequestClient.adminApproval(leaveRequestId, status, approved_by);
    
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Status updated");
            } else {
                System.out.println("Failed to update status. Reason: " + response.getBody());
            }
        } catch (Exception e) {
            System.out.println("Failed to perform admin approval: " + e.getMessage());
        }
    }


    @Override
    public List<LeaveRequest> getAllLeaveRequests(int studentId) {
        try {
            ResponseEntity<Object> response = leaveRequestClient.readHistory(studentId);
            if (response.getStatusCode() == HttpStatus.OK) {
                return (List<LeaveRequest>) response.getBody();
            } else {
                System.out.println("Failed to fetch leave requests for student ID: " + studentId);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Failed to get all leave requests: " + e.getMessage());
            return null;
        }
    }


    @Override
    public List<LeaveRequest> getAllApprovedRequests(int studentId) {
        try {
            ResponseEntity<Object> response = leaveRequestClient.readApprovedRequests(studentId);
            if (response.getStatusCode() == HttpStatus.OK) {
                return (List<LeaveRequest>) response.getBody();
            } else {
                System.out.println("Failed to fetch approved leave requests for student ID: " + studentId);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Failed to get all approved requests: " + e.getMessage());
            return null;
        }
    }


    @Override
    public List<LeaveRequest> getAllRejectedRequests(int studentId) {
        try {
            ResponseEntity<Object> response = leaveRequestClient.readRejectdRequests(studentId);
            if (response.getStatusCode() == HttpStatus.OK) {
                return (List<LeaveRequest>) response.getBody();
            } else {
                System.out.println("Failed to fetch rejected leave requests for student ID: " + studentId);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Failed to get all rejected requests: " + e.getMessage());
            return null;
        }
    }


    @Override
    public List<LeaveRequest> getAllPendingRequests(int studentId) {
        try {
            ResponseEntity<Object> response = leaveRequestClient.readPendingRequests(studentId);
            if (response.getStatusCode() == HttpStatus.OK) {
                return (List<LeaveRequest>) response.getBody();
            } else {
                System.out.println("Failed to fetch pending leave requests for student ID: " + studentId);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Failed to get all pending requests: " + e.getMessage());
            return null;
        }
    }


    @Override
    public List<LeaveRequest> getPendingRequests() {
        try {
            List<LeaveRequest> response = leaveRequestClient.readAllPendingRequests();
            if (response != null) {
                return response;
            } else {
                System.out.println("Failed to fetch pending leave requests");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Failed to get pending requests: " + e.getMessage());
            return null;
        }
    }

}
