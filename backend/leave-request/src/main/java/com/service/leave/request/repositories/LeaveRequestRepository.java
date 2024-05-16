package com.service.leave.request.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.service.leave.request.entities.LeaveRequest;


@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Integer>{
	@Query(value ="select * from leave_requests lr where lr.studentid = ?1 AND lr.status=\"APPROVED\"", nativeQuery = true)
    List<LeaveRequest> getLeaveRequestsByStatus_Approved(int studentid);
    @Query(value ="select * from leave_requests lr where lr.studentid = ?1 AND lr.status=\"REJECTED\"", nativeQuery = true)
    List<LeaveRequest> getLeaveRequestsByStatus_Rejected(int studentid);
    @Query(value ="select * from leave_requests lr where lr.studentid = ?1 AND lr.status=\"PENDING\"", nativeQuery = true)
    List<LeaveRequest> getLeaveRequestsByStatus_Pending(int studentid);
    @Query(value ="select * from leave_requests lr where lr.studentid = ?1 ", nativeQuery = true)
	List<LeaveRequest> getAllLeaveRequests(int studentid);

    @Modifying
    @Query(value = "INSERT INTO leave_requests (studentid, start_date, end_date, reason, status,created_at) " +
                   "VALUES (:studentId, :startDate, :endDate, :reason, :status,:created_at)", nativeQuery = true)
    int submitRequestForm(@Param("studentId") int studentId,
                          @Param("startDate") LocalDate startDate,
                          @Param("endDate") LocalDate endDate,
                          @Param("reason") String reason,
                          @Param("status") String status,
                          @Param("created_at") LocalDate createdAt);
    
    @Modifying
    @Query(value = "UPDATE leave_requests  SET approved_by = :approved_by, status = :status ,updated_at=:updated_at " +
                   "WHERE leave_request_id = :leave_request_id", nativeQuery = true)
    int adminApproval(@Param("leave_request_id") int leaveRequestId,
                           @Param("approved_by") String approvedBy,
                           @Param("status") String status,
                           @Param("updated_at") LocalDate updatedAt);

    @Query(value ="select * from leave_requests lr where lr.status=\"PENDING\"", nativeQuery = true)
    List<LeaveRequest> getLeaveRequestsByStatus_Pending();

}
