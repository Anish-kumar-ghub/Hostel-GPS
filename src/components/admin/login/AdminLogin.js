// AdminLogin.js
import React, { useState } from "react";
import axios from "axios";
import { Container, Form, Button } from 'react-bootstrap';
import "./AdminLogin.css"; // Import the CSS file
import { useNavigate } from "react-router-dom";

const AdminLogin = () => {
  const [emailId, setEmailId] = useState("");
  const [password, setPassword] = useState("");

  const navigator=useNavigate();
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:9003/admin/login", {
        emailId,
        password,
      });
      // Handle successful login
      if(response.data){
        navigator("/admin-home");
      }
      else{
        alert("Wrong credentials");
      }
      console.log(response.data);
    } catch (error) {
      // Handle login error
      alert("Error during login. Please try again");
      console.error("Login failed:", error.response.data);
    }
  };

  return (
    <div className="login-container" >
      
      <Container>
        <h1 className="login-heading"><strong>Admin Login</strong></h1>
        <Form className="login-form" onSubmit={handleSubmit}>
          <Form.Group controlId="formBasicEmail">
            <Form.Label><strong>Email address</strong></Form.Label>
            <Form.Control
              type="email"
              placeholder="Enter email"
              value={emailId}
              onChange={(e) => setEmailId(e.target.value)}
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

export default AdminLogin;
