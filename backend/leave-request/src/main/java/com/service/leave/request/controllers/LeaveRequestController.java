package com.service.leave.request.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.leave.request.entities.LeaveRequest;
import com.service.leave.request.service.LeaveRequestService;

@RestController
@RequestMapping("/leaveRequest")
public class LeaveRequestController {
    
    @Autowired
    private LeaveRequestService leaveRequestService;

    @RequestMapping(value = "/leaveHistory", method = RequestMethod.GET)
    public ResponseEntity<Object> readHistory(@RequestParam("studentid") int studentId) {
        try {
            List<LeaveRequest> leaveRequests = leaveRequestService.getAllLeaveRequests(studentId);
            if (leaveRequests.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No leave requests found for the student");
            } else {
                return ResponseEntity.ok(leaveRequests);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch leave history: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/leaveHistory/approved", method = RequestMethod.GET)
    public ResponseEntity<Object> readApprovedRequests(@RequestParam("studentid") int studentid) {
        try {
            List<LeaveRequest> leaveRequests = leaveRequestService.getAllApprovedRequests(studentid);
            if (leaveRequests.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No approved requests found for the student");
            } else {
                return ResponseEntity.ok(leaveRequests);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch approved leave requests: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/leaveHistory/rejected", method = RequestMethod.GET)
    public ResponseEntity<Object> readRejectedRequests(@RequestParam("studentid") int studentid) {
        try {
            List<LeaveRequest> leaveRequests = leaveRequestService.getAllRejectedRequests(studentid);
            if (leaveRequests.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No rejected requests found for the student");
            } else {
                return ResponseEntity.ok(leaveRequests);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch rejected leave requests: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/leaveHistory/pending", method = RequestMethod.GET)
    public ResponseEntity<Object> readPendingRequests(@RequestParam("studentid") int studentid) {
        try {
            List<LeaveRequest> leaveRequests = leaveRequestService.getAllPendingRequests(studentid);
            if (leaveRequests.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No pending requests found for the student");
            } else {
                return ResponseEntity.ok(leaveRequests);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch pending leave requests: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/approval", method = RequestMethod.POST)
    public ResponseEntity<String> adminApproval(@RequestParam("request_id") int request_id, @RequestParam("status") String status, @RequestParam("approved_by") String approved_by) {
        try {
            LocalDate updatedAt = LocalDate.now();
            int updatedRows = leaveRequestService.adminApproval(request_id, status, approved_by, updatedAt);
            if (updatedRows > 0) {
                return ResponseEntity.ok("Successfully updated approval status");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid request ID or status");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process approval: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/leaveHistory/allpending", method = RequestMethod.GET)
    public ResponseEntity<Object> readAllPendingRequests() {
        try {
            List<LeaveRequest> leaveRequests = leaveRequestService.getPendingRequests();
            if (leaveRequests.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No pending requests found");
            } else {
                return ResponseEntity.ok(leaveRequests);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch all pending requests: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public ResponseEntity<String> submitRequestForm(@RequestParam("studentid") int studentId, @RequestParam("start_date") String startDate, @RequestParam("end_date") String endDate, @RequestParam("reason") String reason) {
        try {
            LocalDate createdAt = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate1 = LocalDate.parse(startDate, formatter);
            LocalDate endDate1 = LocalDate.parse(endDate, formatter);
            int status = leaveRequestService.submitRequestForm(studentId, startDate1, endDate1, reason, "PENDING", createdAt);
            if (status > 0) {
                return ResponseEntity.ok("Leave request submitted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid student ID or date format");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to submit leave request: " + e.getMessage());
        }
    }
}
