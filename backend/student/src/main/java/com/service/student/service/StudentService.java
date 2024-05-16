package com.service.student.service;

import java.util.List;

import com.service.student.entities.LeaveRequest;
import com.service.student.entities.Student;

public interface StudentService {
	public Student findByEmailIdAndPassword(String emailId, String password);
	public void  addStudent(Student student);
	public void submitLeaveRequest(int studentId,String startDate,String endDate,String reason);
	public List<LeaveRequest> getAllLeaveRequests(int studentId);
	public List<LeaveRequest> getAllApprovedRequests(int studentId);
	public List<LeaveRequest> getAllRejectedRequests(int studentId);
	public List<LeaveRequest> getAllPendingRequests(int studentId);
}
