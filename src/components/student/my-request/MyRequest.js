// LeaveRequestList.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Navbar from "../navbar/Navbar";
import './MyRequest.css';
import { useNavigate } from 'react-router-dom';

const MyRequest = () => {
  const [leaveRequests, setLeaveRequests] = useState([]);
  const [filteredRequests, setFilteredRequests] = useState([]);
  const [statusFilter, setStatusFilter] = useState('all');
  const studentId=localStorage.getItem("studentId");
  const navigate=useNavigate();
  useEffect(() => {
    fetchLeaveRequests();
  }, []);

  const fetchLeaveRequests = async () => {
    try {
      if(!studentId){
        alert("Error in fetching in student ID");
      }
      const response = await axios.get(`http://localhost:9004/student/leaveRequests?studentId=${studentId}`);
      setLeaveRequests(response.data);
      setFilteredRequests(response.data);
    } catch (error) {
      console.error('Error fetching leave requests:', error);
    }
  };

  const handleFilterChange = (e) => {
    const status = e.target.value;
    setStatusFilter(status);
    if (status === 'all') {
      setFilteredRequests(leaveRequests);
    } else {
      const filtered = leaveRequests.filter(request => request.status === status);
      setFilteredRequests(filtered);
    }
  };

  return (
    <div >
      <Navbar/>
      <div className='pag'>
      <h2>Leave Requests</h2>
        <div className="filter">
          <label htmlFor="statusFilter">Filter by Status:</label>
          <select id="statusFilter" value={statusFilter} onChange={handleFilterChange}>
            <option value="all">All</option>
            <option value="PENDING">Pending</option>
            <option value="APPROVED">Approved</option>
            <option value="REJECTED">Rejected</option>
          </select>
        </div>
        <table className="table table-striped table-bordere">
          <thead>
            <tr>
              <th>Start Date</th>
              <th>End Date</th>
              <th>Reason</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {filteredRequests.map(request => (
              <tr key={request.id}>
                <td>{request.startDate}</td>
                <td>{request.endDate}</td>
                <td>{request.reason}</td>
                <td>{request.status}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default MyRequest;