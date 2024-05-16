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

import com.service.student.controller.StudentController;
import com.service.student.entities.LeaveRequest;
import com.service.student.entities.Login;
import com.service.student.entities.Student;
import com.service.student.entities.StudentLeave;
import com.service.student.service.StudentService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void testSignup_Success() {
        // Mocking data
        Student student = new Student();
        when(studentService.findByEmailIdAndPassword(anyString(), anyString())).thenReturn(null);

        // Call signup controller method
        ResponseEntity<String> response = studentController.signup(student);

        // Verify result
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testSignup_Failure() {
        // Mocking data
        Student student = new Student();
        when(studentService.findByEmailIdAndPassword(anyString(), anyString())).thenReturn(new Student());

        // Call signup controller method
        ResponseEntity<String> response = studentController.signup(student);

        // Verify result
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testLogin_Success() {
        // Mocking data
        Login login = new Login();
        when(studentService.findByEmailIdAndPassword(anyString(), anyString())).thenReturn(new Student());

        // Call login controller method
        ResponseEntity<Student> response = studentController.login(login);

        // Verify result
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testLogin_Failure() {
        // Mocking data
        Login login = new Login();
        when(studentService.findByEmailIdAndPassword(anyString(), anyString())).thenReturn(null);

        // Call login controller method
        ResponseEntity<Student> response = studentController.login(login);

        // Verify result
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void testSubmitLeaveRequest_Success() {
        // Mocking data
        StudentLeave studentLeave = new StudentLeave();
        doNothing().when(studentService).submitLeaveRequest(anyInt(), anyString(), anyString(), anyString());

        // Call submitLeaveRequest controller method
        ResponseEntity<String> response = studentController.submitLeaveRequest(studentLeave);

        // Verify result
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetAllLeaveRequests_Success() {
        // Mocking data
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        when(studentService.getAllLeaveRequests(anyInt())).thenReturn(leaveRequests);

        // Call getAllLeaveRequests controller method
        ResponseEntity<Object> response = studentController.getAllLeaveRequests(123);

        // Verify result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(leaveRequests, response.getBody());
    }
}
