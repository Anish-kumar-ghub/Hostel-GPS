import React, { useState } from 'react';
import axios from 'axios';
import AdminNavbar from "../navbar/AdminNavbar";

const SearchRequest = () => {
  const [leaveRequests, setLeaveRequests] = useState([]);
  const [filteredRequests, setFilteredRequests] = useState([]);
  const [statusFilter, setStatusFilter] = useState('all');
  const [studentId, setStudentId] = useState('');
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');


  // useEffect(() => {
  //   if (studentId) {
  //     fetchLeaveRequests();
  //   }
  // }, [studentId]);

  const fetchLeaveRequests = async () => {
    try {
      setLoading(true);
      const response = await axios.get(`http://localhost:9003/admin/leaveRequests?studentId=${studentId}`);
      if (response.data.length === 0) {
        setErrorMessage('No leave requests found for the student');
      } else {
        setLeaveRequests(response.data);
        setFilteredRequests(response.data);
        setErrorMessage('');
      }
    } catch (error) {
      console.error('Error fetching leave requests:', error);
      setErrorMessage('Failed to fetch leave requests. Please try again.');
    } finally {
      setLoading(false);
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

  const handleStudentIDChange = (e) => {
    setStudentId(e.target.value);
  };

  return (
    <>
    <AdminNavbar/>
      <div className='pag'>
        
        <div className="new-request">
          <form className="row g-3">
            <div className="col-auto">
              <label htmlFor="inputStudentId" className="visually-hidden">Student ID</label>
              <input 
                type="number" 
                className="form-control" 
                id="inputStudentId" 
                placeholder="Enter Student ID" 
                value={studentId} 
                onChange={handleStudentIDChange} 
              />
            </div>
            <div className="col-auto">
              <button type="button" className="btn btn-primary mb-3" onClick={fetchLeaveRequests}>Search</button>
            </div>
          </form>
          {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
          <h2>Leave Requests</h2>
          {loading && <div>Loading...</div>}
          {!loading && !errorMessage && (
            <>
              <div className="filter">
                <label htmlFor="statusFilter">Filter by Status:</label>
                <select id="statusFilter" value={statusFilter} onChange={handleFilterChange}>
                  <option value="all">All</option>
                  <option value="PENDING">Pending</option>
                  <option value="APPROVED">Approved</option>
                  <option value="REJECTED">Rejected</option>
                </select>
              </div>
              <table className="table table-striped table-bordered">
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
            </>
          )}
        </div>
      </div>
    </>
  );
};

export default SearchRequest;
