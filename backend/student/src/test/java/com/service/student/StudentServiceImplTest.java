package com.service.student;

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

import com.service.student.entities.LeaveRequest;
import com.service.student.repositories.StudentRepository;
import com.service.student.service.LeaveRequestClient;
import com.service.student.service.StudentServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private LeaveRequestClient leaveRequestClient;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    public void testSubmitLeaveRequest_Success() {
        // Mocking data
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Success", HttpStatus.OK);
        when(leaveRequestClient.submitRequestForm(anyInt(), anyString(), anyString(), anyString())).thenReturn(responseEntity);

        // Call submitLeaveRequest service method
        studentService.submitLeaveRequest(123, "2024-05-01", "2024-05-05", "Vacation");

        // Verify interaction
        verify(leaveRequestClient, times(1)).submitRequestForm(123, "2024-05-01", "2024-05-05", "Vacation");
    }

    @Test
    public void testSubmitLeaveRequest_Failure() {
        // Mocking data
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        when(leaveRequestClient.submitRequestForm(anyInt(), anyString(), anyString(), anyString())).thenReturn(responseEntity);

        // Call submitLeaveRequest service method
        studentService.submitLeaveRequest(123, "2024-05-01", "2024-05-05", "Vacation");

        // Verify interaction
        verify(leaveRequestClient, times(1)).submitRequestForm(123, "2024-05-01", "2024-05-05", "Vacation");
    }

    @Test
    public void testGetAllLeaveRequests_Success() {
        // Mocking data
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        when(leaveRequestClient.readHistory(anyInt())).thenReturn(ResponseEntity.ok(leaveRequests));

        // Call getAllLeaveRequests service method
        List<LeaveRequest> result = studentService.getAllLeaveRequests(123);

        // Verify result
        assertEquals(leaveRequests, result);
    }

    @Test
    public void testGetAllApprovedRequests_Success() {
        // Mocking data
        List<LeaveRequest> approvedRequests = new ArrayList<>();
        when(leaveRequestClient.readApprovedRequests(anyInt())).thenReturn(ResponseEntity.ok(approvedRequests));

        // Call getAllApprovedRequests service method
        List<LeaveRequest> result = studentService.getAllApprovedRequests(123);

        // Verify result
        assertEquals(approvedRequests, result);
    }

    @Test
    public void testGetAllRejectedRequests_Success() {
        // Mocking data
        List<LeaveRequest> rejectedRequests = new ArrayList<>();
        when(leaveRequestClient.readRejectdRequests(anyInt())).thenReturn(ResponseEntity.ok(rejectedRequests));

        // Call getAllRejectedRequests service method
        List<LeaveRequest> result = studentService.getAllRejectedRequests(123);

        // Verify result
        assertEquals(rejectedRequests, result);
    }

    @Test
    public void testGetAllPendingRequests_Success() {
        // Mocking data
        List<LeaveRequest> pendingRequests = new ArrayList<>();
        when(leaveRequestClient.readPendingRequests(anyInt())).thenReturn(ResponseEntity.ok(pendingRequests));

        // Call getAllPendingRequests service method
        List<LeaveRequest> result = studentService.getAllPendingRequests(123);

        // Verify result
        assertEquals(pendingRequests, result);
    }
}
