package com.service.admin.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.service.admin.entities.LeaveRequest;


//@FeignClient(url="http://localhost:9002",value="Leave-request-Client")
@FeignClient(name="LEAVE-REQUEST")
@Service
public interface LeaveRequestClient {
	
		
		@RequestMapping(value = "/leaveRequest/leaveHistory", method = RequestMethod.GET)
		public ResponseEntity<Object> readHistory(@RequestParam("studentid") int studentId);

		@RequestMapping(value = "/leaveRequest/leaveHistory/approved", method = RequestMethod.GET)
		public ResponseEntity<Object> readApprovedRequests(@RequestParam("studentid") int studentid);
		
		@RequestMapping(value = "/leaveRequest/leaveHistory/rejected", method = RequestMethod.GET)
		public ResponseEntity<Object> readRejectdRequests(@RequestParam("studentid") int studentid);
		
		@RequestMapping(value = "/leaveRequest/leaveHistory/pending", method = RequestMethod.GET)
		public ResponseEntity<Object> readPendingRequests(@RequestParam("studentid") int studentid);
		
		@RequestMapping(value="/leaveRequest/leaveHistory/allpending", method= RequestMethod.GET)
		public List<LeaveRequest> readAllPendingRequests();
		
		@RequestMapping(value = "/leaveRequest/approval", method = RequestMethod.POST)
		public ResponseEntity<String> adminApproval(@RequestParam("request_id") int request_id, @RequestParam("status") String status,@RequestParam("approved_by") String approved_by);
}
