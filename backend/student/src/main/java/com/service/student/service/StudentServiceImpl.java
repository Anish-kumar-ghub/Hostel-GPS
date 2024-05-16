package com.service.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.service.student.repositories.StudentRepository;
import com.service.student.entities.LeaveRequest;
import com.service.student.entities.Student;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    LeaveRequestClient leaveRequestClient;

    @Override
    public Student findByEmailIdAndPassword(String emailId, String password) {
        try {
            return studentRepository.findByEmailIdAndPassword(emailId, password);
        } catch (Exception e) {
            System.out.println("Failed to find student by email and password: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void addStudent(Student student) {
        try {
            studentRepository.save(student);
        } catch (Exception e) {
            System.out.println("Failed to add student: " + e.getMessage());
        }
    }

    @Override
    public void submitLeaveRequest(int studentId, String startDate, String endDate, String reason) {
        try {
            ResponseEntity<String> response = leaveRequestClient.submitRequestForm(studentId, startDate, endDate, reason);

            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Leave request submitted successfully.");
            } else {
                System.out.println("Failed to submit leave request. Reason: " + response.getBody());
            }
        } catch (Exception e) {
            System.out.println("Failed to submit leave request: " + e.getMessage());
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
}
