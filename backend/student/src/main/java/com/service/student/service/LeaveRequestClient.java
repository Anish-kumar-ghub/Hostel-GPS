package com.service.student.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(url="http://localhost:9002",value="Leave-request-Client")
@FeignClient(name="LEAVE-REQUEST")
@Service
public interface LeaveRequestClient {
	
		@RequestMapping(value = "/leaveRequest/submit", method = RequestMethod.POST)
		public ResponseEntity<String> submitRequestForm(@RequestParam("studentid") int studentId,@RequestParam("start_date") String startDate,@RequestParam("end_date")  String endDate,@RequestParam("reason") String reason);

		@RequestMapping(value = "/leaveRequest/leaveHistory", method = RequestMethod.GET)
		public ResponseEntity<Object> readHistory(@RequestParam("studentid") int studentId);

		@RequestMapping(value = "/leaveRequest/leaveHistory/approved", method = RequestMethod.GET)
		public ResponseEntity<Object> readApprovedRequests(@RequestParam("studentid") int studentid);
		
		@RequestMapping(value = "/leaveRequest/leaveHistory/rejected", method = RequestMethod.GET)
		public ResponseEntity<Object> readRejectdRequests(@RequestParam("studentid") int studentid);
		
		@RequestMapping(value = "/leaveRequest/leaveHistory/pending", method = RequestMethod.GET)
		public ResponseEntity<Object> readPendingRequests(@RequestParam("studentid") int studentid);
		
		
		
}
