package com.service.leave.request.service;

import java.time.LocalDate;
import java.util.List;

import com.service.leave.request.entities.LeaveRequest;



public interface LeaveRequestService {
	public int submitRequestForm(int studentId, LocalDate startDate, LocalDate endDate, String reason, String status,
			LocalDate created_at);

	public int adminApproval(int LeaveRequestId, String status, String approved_by,	LocalDate updatedAt);
	
	public List<LeaveRequest> getAllLeaveRequests(int studentid);

	public List<LeaveRequest> getAllApprovedRequests(int studentid);

	public List<LeaveRequest> getAllRejectedRequests(int studentid);

	public List<LeaveRequest> getAllPendingRequests(int studentid);

	public List<LeaveRequest> getPendingRequests();

}
