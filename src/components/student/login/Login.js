import axios from 'axios';
import React, { useState } from 'react';
import { Container, Form, Button } from 'react-bootstrap';
import './Login.css'; // Import the CSS file
import { useNavigate } from 'react-router-dom';
// import SignUp from '../signup/SignUp';

const Login = () => {
  const [emailId, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const navigator = useNavigate();
  const handleSubmit = async(e) => {

    e.preventDefault();
    // Add your logic
    try {
      const response = await axios.post('http://localhost:9004/student/login', { emailId, password });
      if (response.data) {
        const studentId=response.data.studentID;
        localStorage.setItem("studentId",studentId);
          // Redirect to Home page upon successful login
          navigator('/home');
      } else {
          alert('Wrong credentials');
      }
  } catch (error) {
      console.error('Error during login:', error);
      alert('Error during login. Please try again.');
  }
  };

  return (
    <div className="login-container" >
      
      <Container>
        <h1 className="login-heading"><strong>Login</strong></h1>
        <Form className="login-form" onSubmit={handleSubmit}>
          <Form.Group controlId="formBasicEmail">
            <Form.Label><strong>Email address</strong></Form.Label>
            <Form.Control
              type="email"
              placeholder="Enter email"
              value={emailId}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </Form.Group>

          <Form.Group controlId="formBasicPassword">
            <Form.Label><strong>Password</strong></Form.Label>
            <Form.Control
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </Form.Group>

          <Button variant="primary" type="submit">
            Submit
          </Button>
           
          
        </Form>
      </Container>
    </div>
  );
};

export default Login;