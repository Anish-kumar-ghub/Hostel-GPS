package com.service.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.student.entities.LeaveRequest;
import com.service.student.entities.Login;
import com.service.student.entities.Student;
import com.service.student.entities.StudentLeave;
import com.service.student.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Student student) {
        try {
            if (studentService.findByEmailIdAndPassword(student.getEmailId(), student.getPassword()) != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
            }
            studentService.addStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body("Signup successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Signup failed");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Student> login(@RequestBody Login obj) {
        try {
            String emailId = obj.getEmailId();
            String password = obj.getPassword();
            Student student = studentService.findByEmailIdAndPassword(emailId, password);
            if (student != null) {
                return ResponseEntity.ok(student);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping("/submitLeaveRequest")
    public ResponseEntity<String> submitLeaveRequest(@RequestBody StudentLeave obj) {
        try {
            int studentId = obj.getStudentId(); 
            String startDate = obj.getStartDate();
            String endDate = obj.getEndDate();
            String reason = obj.getReason();
            studentService.submitLeaveRequest(studentId, startDate, endDate, reason);
            return ResponseEntity.ok("Leave request submitted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to submit leave request: " + e.getMessage());
        }
    }
    
    @GetMapping("/leaveRequests")
    public ResponseEntity<Object> getAllLeaveRequests(@RequestParam int studentId) {
        try {
            List<LeaveRequest> allLeaveRequest = studentService.getAllLeaveRequests(studentId);
            return ResponseEntity.ok(allLeaveRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get all leave requests: " + e.getMessage());
        }
    }

    @GetMapping("/leaveRequests/approved")
    public ResponseEntity<Object> getAllApprovedRequests(@RequestParam int studentId) {
        try {
            List<LeaveRequest> allApprovedRequest = studentService.getAllApprovedRequests(studentId);
            return ResponseEntity.ok(allApprovedRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get all approved requests: " + e.getMessage());
        }
    }

    @GetMapping("/leaveRequests/rejected")
    public ResponseEntity<Object> getAllRejectedRequests(@RequestParam int studentId) {
        try {
            List<LeaveRequest> allRejectedRequest = studentService.getAllRejectedRequests(studentId);
            return ResponseEntity.ok(allRejectedRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get all rejected requests: " + e.getMessage());
        }
    }

    @GetMapping("/leaveRequests/pending")
    public ResponseEntity<Object> getAllPendingRequests(@RequestParam int studentId) {
        try {
            List<LeaveRequest> allPendingRequest = studentService.getAllPendingRequests(studentId);
            return ResponseEntity.ok(allPendingRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get all pending requests: " + e.getMessage());
        }
    }
}
