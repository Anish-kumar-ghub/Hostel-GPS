// LeaveRequestList.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './LeaveRequest.css';
import AdminNavbar from '../navbar/AdminNavbar' 
const MyRequest = () => {
  // const [leaveRequests, setLeaveRequests] = useState([]);
  const [filteredRequests, setFilteredRequests] = useState([]);
  // const [statusFilter, setStatusFilter] = useState('all');

  useEffect(() => {
    fetchLeaveRequests();
  }, []);

  const fetchLeaveRequests = async () => {
    try {
      const response = await axios.get(`http://localhost:9091/admin/leaveRequests/allpending`);
      // setLeaveRequests(response.data);
      setFilteredRequests(response.data);
    } catch (error) {
      console.error('Error fetching leave requests:', error);
    }
  };

  // const handleFilterChange = (e) => {
  //   const status = e.target.value;
  //   setStatusFilter(status);
  //   if (status === 'all') {
  //     setFilteredRequests(leaveRequests);
  //   } else {
  //     const filtered = leaveRequests.filter(request => request.status === status);
  //     setFilteredRequests(filtered);
  //   }
  // };

  return (
    <div >
      <AdminNavbar/>
      <div className='pag'>
      <h2>Leave Requests</h2>
        {/* <div className="filter">
          <label htmlFor="statusFilter">Filter by Status:</label>
          <select id="statusFilter" value={statusFilter} onChange={handleFilterChange}>
            <option value="all">All</option>
            <option value="PENDING">All Pending</option>
            <option value="APPROVED">Approved</option>
            <option value="REJECTED">Rejected</option>
          </select>
        </div> */}
        <table className="table table-striped table-bordered">
          <thead>
            <tr>
              <th>Student ID</th>
              <th>Start Date</th>
              <th>End Date</th>
              <th>Reason</th>
              <th>Status</th>
              <th>Created on</th>
              <th>Request ID</th>
            </tr>
          </thead>
          <tbody>
            {filteredRequests.map(request => (
              <tr key={request.id}>
                <td>{request.studentID}</td>
                <td>{request.startDate}</td>
                <td>{request.endDate}</td>
                <td>{request.reason}</td>
                <td>{request.status}</td>
                <td>{request.createdAt}</td>
                <td>{request.leaveRequestId}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default MyRequest;