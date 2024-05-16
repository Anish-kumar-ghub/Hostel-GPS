package com.service.leave.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.service.leave.request.entities.LeaveRequest;
import com.service.leave.request.repositories.LeaveRequestRepository;
import com.service.leave.request.service.LeaveRequestServiceImpl;

@ExtendWith(MockitoExtension.class)
public class LeaveRequestServiceImplTest {

    @Mock
    private LeaveRequestRepository leaveRequestRepository;

    @InjectMocks
    private LeaveRequestServiceImpl leaveRequestService;

    @Test
    public void testSubmitRequestForm_Success() {
        // Mocking data
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(5);
        when(leaveRequestRepository.submitRequestForm(anyInt(), any(LocalDate.class), any(LocalDate.class), anyString(), anyString(), any(LocalDate.class))).thenReturn(1);

        // Call submitRequestForm service method
        int status = leaveRequestService.submitRequestForm(123, startDate, endDate, "Reason", "PENDING", startDate);

        // Verify response
        assert(status == 1);
    }

    @Test
    public void testAdminApproval_Success() {
        // Mocking data
        when(leaveRequestRepository.adminApproval(anyInt(), anyString(), anyString(), any(LocalDate.class))).thenReturn(1);

        // Call adminApproval service method
        int result = leaveRequestService.adminApproval(123, "APPROVED", "Admin", LocalDate.now());

        // Verify result
        assertEquals(1, result);
    }

    @Test
    public void testAdminApproval_NotFound() {
        // Mocking data
        when(leaveRequestRepository.adminApproval(anyInt(), anyString(), anyString(), any(LocalDate.class))).thenReturn(0);

        // Call adminApproval service method
        int result = leaveRequestService.adminApproval(123, "APPROVED", "Admin", LocalDate.now());

        // Verify result
        assertEquals(0, result);
    }

    @Test
    public void testGetAllLeaveRequests() {
        // Mocking data
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        when(leaveRequestRepository.getAllLeaveRequests(anyInt())).thenReturn(leaveRequests);

        // Call getAllLeaveRequests service method
        List<LeaveRequest> result = leaveRequestService.getAllLeaveRequests(123);

        // Verify result
        assertEquals(leaveRequests, result);
    }

    @Test
    public void testGetAllApprovedRequests() {
        // Mocking data
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        when(leaveRequestRepository.getLeaveRequestsByStatus_Approved(anyInt())).thenReturn(leaveRequests);

        // Call getAllApprovedRequests service method
        List<LeaveRequest> result = leaveRequestService.getAllApprovedRequests(123);

        // Verify result
        assertEquals(leaveRequests, result);
    }

    @Test
    public void testGetAllRejectedRequests() {
        // Mocking data
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        when(leaveRequestRepository.getLeaveRequestsByStatus_Rejected(anyInt())).thenReturn(leaveRequests);

        // Call getAllRejectedRequests service method
        List<LeaveRequest> result = leaveRequestService.getAllRejectedRequests(123);

        // Verify result
        assertEquals(leaveRequests, result);
    }

    @Test
    public void testGetAllPendingRequests() {
        // Mocking data
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        when(leaveRequestRepository.getLeaveRequestsByStatus_Pending(anyInt())).thenReturn(leaveRequests);

        // Call getAllPendingRequests service method
        List<LeaveRequest> result = leaveRequestService.getAllPendingRequests(123);

        // Verify result
        assertEquals(leaveRequests, result);
    }

    @Test
    public void testGetPendingRequests() {
        // Mocking data
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        when(leaveRequestRepository.getLeaveRequestsByStatus_Pending()).thenReturn(leaveRequests);

        // Call getPendingRequests service method
        List<LeaveRequest> result = leaveRequestService.getPendingRequests();

        // Verify result
        assertEquals(leaveRequests, result);
    }
}
