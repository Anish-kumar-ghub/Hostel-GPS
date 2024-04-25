
import React, { useState } from 'react';
import axios from 'axios';
import './Approval.css';
import AdminNavbar from '../navbar/AdminNavbar' 
const Approval = () => {
  const [requestId, setRequestId] = useState('');
  const [status, setStatus] = useState('');
  const [approvedBy, setApprovedBy] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:9003/admin/leaveRequests/approval', {
        requestId,
        status,
        approvedBy
      });
      // Handle successful approval/rejection
      setMessage(response.data);
    } catch (error) {
      // Handle error
      setMessage(error.response.data);
    }
  };

  return (
    <>
    <AdminNavbar/>
    <div className="m">
      <div className="leave-request-approval">
        <h2>Leave Request Approval</h2>
        <form onSubmit={handleSubmit}>
          <label htmlFor="requestId">Request ID:</label>
          <input
            type="text"
            id="requestId"
            value={requestId}
            onChange={(e) => setRequestId(e.target.value)}
            required
          />

          <label htmlFor="status">Status:</label>
          <select
            id="status"
            value={status}
            onChange={(e) => setStatus(e.target.value)}
            required
          >
            <option value="">Select Status</option>
            <option value="APPROVED">Approved</option>
            <option value="REJECTED">Rejected</option>
          </select>

          <label htmlFor="approvedBy">Approved By:</label>
          <input
            type="text"
            id="approvedBy"
            value={approvedBy}
            onChange={(e) => setApprovedBy(e.target.value)}
            required
          />

          <button type="submit">Submit</button>
        </form>

        {message && <div className="message">{message}</div>}
      </div>
    </div>
    </>
  );
};

export default Approval;
