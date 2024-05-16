package com.service.leave.request.entities;

public class ApprovalRequest {
	private int leaveRequestId;
    private String status;
    private String approvedBy;
    
	public int getLeaveRequestId() {
		return leaveRequestId;
	}
	public void setLeaveRequestId(int leaveRequestId) {
		this.leaveRequestId = leaveRequestId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
}
