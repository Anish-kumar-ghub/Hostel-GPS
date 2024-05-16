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

import com.service.admin.entities.LeaveRequest;
import com.service.admin.repositories.AdminRepository;
import com.service.admin.services.LeaveRequestClient;
import com.service.admin.services.AdminServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceImplTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private LeaveRequestClient leaveRequestClient;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    public void testAdminApproval_Success() {
        // Mocking data
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Success", HttpStatus.OK);
        when(leaveRequestClient.adminApproval(anyInt(), anyString(), anyString())).thenReturn(responseEntity);

        // Call adminApproval service method
        adminService.adminApproval(123, "APPROVED", "Admin");

        // Verify interaction
        verify(leaveRequestClient, times(1)).adminApproval(123, "APPROVED", "Admin");
    }

    @Test
    public void testAdminApproval_Failure() {
        // Mocking data
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        when(leaveRequestClient.adminApproval(anyInt(), anyString(), anyString())).thenReturn(responseEntity);

        // Call adminApproval service method
        adminService.adminApproval(123, "APPROVED", "Admin");

        // Verify interaction
        verify(leaveRequestClient, times(1)).adminApproval(123, "APPROVED", "Admin");
    }

    @Test
    public void testGetAllLeaveRequests_Success() {
        // Mocking data
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        when(leaveRequestClient.readHistory(anyInt())).thenReturn(ResponseEntity.ok(leaveRequests));

        // Call getAllLeaveRequests service method
        List<LeaveRequest> result = adminService.getAllLeaveRequests(123);

        // Verify result
        assertEquals(leaveRequests, result);
    }

}
