package com.service.leave.request;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.service.leave.request.controllers.LeaveRequestController;
import com.service.leave.request.entities.LeaveRequest;
import com.service.leave.request.service.LeaveRequestService;

@ExtendWith(MockitoExtension.class)
public class LeaveRequestControllerTest {

    @Mock
    private LeaveRequestService leaveRequestService;

    @InjectMocks
    private LeaveRequestController leaveRequestController;

    @Test
    public void testReadHistory_Success() {
        // Mocking data
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        when(leaveRequestService.getAllLeaveRequests(anyInt())).thenReturn(leaveRequests);

        // Call readHistory endpoint
        ResponseEntity<Object> response = leaveRequestController.readHistory(123);

        // Verify response
        assert(response.getStatusCode().equals(HttpStatus.OK));
        assert(response.getBody().equals(leaveRequests));
    }

    @Test
    public void testReadHistory_NotFound() {
        // Mocking data
        when(leaveRequestService.getAllLeaveRequests(anyInt())).thenReturn(null);

        // Call readHistory endpoint
        ResponseEntity<Object> response = leaveRequestController.readHistory(123);

        // Verify response
        assert(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
        assert(response.getBody().equals("No leave requests found for the student"));
    }

    @Test
    public void testReadApprovedRequests_Success() throws Exception {
        // Mocking data
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        when(leaveRequestService.getAllApprovedRequests(anyInt())).thenReturn(leaveRequests);

        // Call readApprovedRequests endpoint
        ResponseEntity<Object> response = leaveRequestController.readApprovedRequests(123);

        // Verify response
        assert(response.getStatusCode().equals(HttpStatus.OK));
        assert(response.getBody().equals(leaveRequests));
    }

    @Test
    public void testReadApprovedRequests_NotFound() throws Exception {
        // Mocking data
        when(leaveRequestService.getAllApprovedRequests(anyInt())).thenReturn(null);

        // Call readApprovedRequests endpoint
        ResponseEntity<Object> response = leaveRequestController.readApprovedRequests(123);

        // Verify response
        assert(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
        assert(response.getBody().equals("No approved requests found for the student"));
    }

    @Test
    public void testReadRejectedRequests_Success() throws Exception {
        // Mocking data
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        when(leaveRequestService.getAllRejectedRequests(anyInt())).thenReturn(leaveRequests);

        // Call readRejectedRequests endpoint
        ResponseEntity<Object> response = leaveRequestController.readRejectedRequests(123);

        // Verify response
        assert(response.getStatusCode().equals(HttpStatus.OK));
        assert(response.getBody().equals(leaveRequests));
    }

    @Test
    public void testReadRejectedRequests_NotFound() throws Exception {
        // Mocking data
        when(leaveRequestService.getAllRejectedRequests(anyInt())).thenReturn(null);

        // Call readRejectedRequests endpoint
        ResponseEntity<Object> response = leaveRequestController.readRejectedRequests(1234);

        // Verify response
        assert(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
        assert(response.getBody().equals("No rejected requests found for the student"));
    }

    @Test
    public void testReadPendingRequests_Success() throws Exception {
        // Mocking data
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        when(leaveRequestService.getAllPendingRequests(anyInt())).thenReturn(leaveRequests);

        // Call readPendingRequests endpoint
        ResponseEntity<Object> response = leaveRequestController.readPendingRequests(123);

        // Verify response
        assert(response.getStatusCode().equals(HttpStatus.OK));
        assert(response.getBody().equals(leaveRequests));
    }

    @Test
    public void testReadPendingRequests_NotFound() throws Exception {
        // Mocking data
        when(leaveRequestService.getAllPendingRequests(anyInt())).thenReturn(null);

        // Call readPendingRequests endpoint
        ResponseEntity<Object> response = leaveRequestController.readPendingRequests(123);

        // Verify response
        assert(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
        assert(response.getBody().equals("No pending requests found for the student"));
    }

    @Test
    public void testAdminApproval_Success() {
        // Mocking data
        when(leaveRequestService.adminApproval(anyInt(), anyString(), anyString(), any())).thenReturn(1);

        // Call adminApproval endpoint
        ResponseEntity<String> response = leaveRequestController.adminApproval(123, "APPROVED", "Admin");

        // Verify response
        assert(response.getStatusCode().equals(HttpStatus.OK));
        assert(response.getBody().equals("Successful"));
    }

    @Test
    public void testAdminApproval_NotFound() {
        // Mocking data
        when(leaveRequestService.adminApproval(anyInt(), anyString(), anyString(), any())).thenReturn(0);

        // Call adminApproval endpoint
        ResponseEntity<String> response = leaveRequestController.adminApproval(123, "APPROVED", "Admin");

        // Verify response
        assert(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
        assert(response.getBody().equals("Invalid"));
    }

}
