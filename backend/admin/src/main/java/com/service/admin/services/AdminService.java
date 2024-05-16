package com.service.admin.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.service.admin.entities.Admin;
import com.service.admin.entities.LeaveRequest;


@Service
public interface AdminService {
	
	public Admin findByEmailIdAndPassword(String emailId, String password);

	public void adminApproval(int LeaveRequestId, String status, String approved_by);
	
	public List<LeaveRequest> getAllLeaveRequests(int studentId);

	public List<LeaveRequest> getAllApprovedRequests(int studentId);

	public List<LeaveRequest> getAllRejectedRequests(int studentId);

	public List<LeaveRequest> getAllPendingRequests(int studentId);

	public List<LeaveRequest> getPendingRequests();

}
