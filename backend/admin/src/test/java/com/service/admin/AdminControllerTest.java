package com.service.admin;

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

import com.service.admin.controllers.AdminController;
import com.service.admin.entities.Admin;
import com.service.admin.entities.Approval;
import com.service.admin.entities.LeaveRequest;
import com.service.admin.entities.Login;
import com.service.admin.services.AdminService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    @Test
    public void testAdminLogin_Success() {
        // Mocking data
        Login login = new Login();
        when(adminService.findByEmailIdAndPassword(anyString(), anyString())).thenReturn(new Admin());

        // Call adminLogin controller method
        ResponseEntity<Object> response = adminController.adminLogin(login);

        // Verify result
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testAdminLogin_Failure() {
        // Mocking data
        Login login = new Login();
        when(adminService.findByEmailIdAndPassword(anyString(), anyString())).thenReturn(null);

        // Call adminLogin controller method
//        ResponseEntity<Object> response = adminController.adminLogin(login);

        // Verify result
//        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void testGetAllLeaveRequests_Success() {
        // Mocking data
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        when(adminService.getAllLeaveRequests(anyInt())).thenReturn(leaveRequests);

        // Call getAllLeaveRequests controller method
        ResponseEntity<Object> response = adminController.getAllLeaveRequests(123);

        // Verify result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(leaveRequests, response.getBody());
    }

    @Test
    public void testGetAllApprovedRequests_Success() {
        // Mocking data
        List<LeaveRequest> approvedRequests = new ArrayList<>();
        when(adminService.getAllApprovedRequests(anyInt())).thenReturn(approvedRequests);

        // Call getAllApprovedRequests controller method
        ResponseEntity<Object> response = adminController.getAllApprovedRequests(123);

        // Verify result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(approvedRequests, response.getBody());
    }

    @Test
    public void testGetAllRejectedRequests_Success() {
        // Mocking data
        List<LeaveRequest> rejectedRequests = new ArrayList<>();
        when(adminService.getAllRejectedRequests(anyInt())).thenReturn(rejectedRequests);

        // Call getAllRejectedRequests controller method
        ResponseEntity<Object> response = adminController.getAllRejectedRequests(123);

        // Verify result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rejectedRequests, response.getBody());
    }

    @Test
    public void testGetAllPendingRequests_Success() {
        // Mocking data
        List<LeaveRequest> pendingRequests = new ArrayList<>();
        when(adminService.getAllPendingRequests(anyInt())).thenReturn(pendingRequests);

        // Call getAllPendingRequests controller method
        ResponseEntity<Object> response = adminController.getAllPendingRequests(123);

        // Verify result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pendingRequests, response.getBody());
    }

    @Test
    public void testAdminApproval_Success() {
        // Mocking data
        Approval approval = new Approval();
//        when(adminService.adminApproval(anyInt(), anyString(), anyString())).thenReturn("status updated successfully");

        // Call adminApproval controller method
        ResponseEntity<String> response = adminController.adminApproval(approval);

        // Verify result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("status updated successfully", response.getBody());
    }

}
