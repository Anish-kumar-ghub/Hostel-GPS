import React, { useState } from 'react';
import axios from 'axios';
import Navbar from "../navbar/Navbar";
import './NewRequest.css'

const NewRequest = () => {
  const [studentId, setStudentId] = useState('');
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [reason, setReason] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:9004/student/submitLeaveRequest', {
        studentId,
        startDate,
        endDate,
        reason
      });
      // Handle successful submission
      setMessage(response.data);
    } catch (error) {
      // Handle submission error
      setMessage("Error submitting leave request");
      console.error("Error: ",error);
      
    }
  };

  return (
    <>
    <Navbar/>
    <div className='page'>
        <div className='new-request'>
        <h2>Submit Leave Request</h2>
        <form onSubmit={handleSubmit}>
            <div className="mb-3">
            <label htmlFor="studentId" className="form-label">Student ID</label>
            <input type="text" className="form-control" id="studentId" value={studentId} onChange={(e) => setStudentId(e.target.value)} required />
            </div>
            <div className="mb-3">
            <label htmlFor="startDate" className="form-label">Start Date</label>
            <input type="date" className="form-control" id="startDate" value={startDate} onChange={(e) => setStartDate(e.target.value)} required />
            </div>
            <div className="mb-3">
            <label htmlFor="endDate" className="form-label">End Date</label>
            <input type="date" className="form-control" id="endDate" value={endDate} onChange={(e) => setEndDate(e.target.value)} required />
            </div>
            <div className="mb-3">
            <label htmlFor="reason" className="form-label">Reason</label>
            <textarea className="form-control" id="reason" rows="3" value={reason} onChange={(e) => setReason(e.target.value)} required></textarea>
            </div>
            <button type="submit" className="btn btn-primary">Submit</button>
        </form>
        {message && <div className="mt-3">{message}</div>}
        </div>
    </div> 
    </>
  );
};

export default NewRequest;