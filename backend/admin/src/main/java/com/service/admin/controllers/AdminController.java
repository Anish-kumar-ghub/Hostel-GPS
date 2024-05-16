package com.service.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.admin.entities.Admin;
import com.service.admin.entities.Approval;
import com.service.admin.entities.LeaveRequest;
import com.service.admin.entities.Login;
import com.service.admin.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> adminLogin(@RequestBody Login obj) {
        try {
            String emailId = obj.getEmailId();
            String password = obj.getPassword();
            Admin admin = adminService.findByEmailIdAndPassword(emailId, password);
            if (admin == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            } else {
                return ResponseEntity.ok(admin);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to login: " + e.getMessage());
        }
    }

    @GetMapping("/leaveRequests")
    public ResponseEntity<Object> getAllLeaveRequests(@RequestParam int studentId) {
        try {
            List<LeaveRequest> allLeaveRequest = adminService.getAllLeaveRequests(studentId);
            return ResponseEntity.ok(allLeaveRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get leave requests: " + e.getMessage());
        }
    }

    @GetMapping("/leaveRequests/approved")
    public ResponseEntity<Object> getAllApprovedRequests(@RequestParam int studentId) {
        try {
            List<LeaveRequest> allApprovedRequest = adminService.getAllApprovedRequests(studentId);
            return ResponseEntity.ok(allApprovedRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get approved requests: " + e.getMessage());
        }
    }

    @GetMapping("/leaveRequests/rejected")
    public ResponseEntity<Object> getAllRejectedRequests(@RequestParam int studentId) {
        try {
            List<LeaveRequest> allRejectedRequest = adminService.getAllRejectedRequests(studentId);
            return ResponseEntity.ok(allRejectedRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get rejected requests: " + e.getMessage());
        }
    }

    @GetMapping("/leaveRequests/pending")
    public ResponseEntity<Object> getAllPendingRequests(@RequestParam int studentId) {
        try {
            List<LeaveRequest> allPendingRequest = adminService.getAllPendingRequests(studentId);
            return ResponseEntity.ok(allPendingRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get pending requests: " + e.getMessage());
        }
    }

    @GetMapping("/leaveRequests/allpending")
    public ResponseEntity<Object> getPendingRequests() {
        try {
            List<LeaveRequest> allPendingRequest = adminService.getPendingRequests();
            return ResponseEntity.ok(allPendingRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get all pending requests: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/leaveRequests/approval", method = RequestMethod.POST)
    public ResponseEntity<String> adminApproval(@RequestBody Approval obj) {
        try {
            int requestId = obj.getRequestId();
            String status = obj.getStatus();
            String approvedBy = obj.getApprovedBy();
            adminService.adminApproval(requestId, status, approvedBy);
            return ResponseEntity.ok("Status updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update status: " + e.getMessage());
        }
    }
}
