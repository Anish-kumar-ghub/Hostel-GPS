import React, { useState } from 'react';
import { Container, Form, Button } from 'react-bootstrap';
import './SignUp.css'; // Import the CSS file

const SignUp = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    // Add your signup logic here
    console.log('Email:', email);
    console.log('Password:', password);
  };

  return (
    <div className="signup-container" >
      
      <Container>
        <h1 className="signup-heading"><strong>Sign Up</strong></h1>
        <Form className="signup-form" onSubmit={handleSubmit}>
          <Form.Group controlId="formBasicEmail">
            <Form.Label><strong>Email address</strong></Form.Label>
            <Form.Control
              type="email"
              placeholder="Enter email"
              value={email}
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
          
          <Button variant="primary" type="button">
            Login
          </Button>

        </Form>
      </Container>
    </div>
  );
};

export default SignUp;