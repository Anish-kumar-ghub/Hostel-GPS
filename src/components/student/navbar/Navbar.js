import React from "react";
import { Link, useNavigate } from "react-router-dom";

const Navigation = () => {
  const navigator=useNavigate();
  const handleLogout = () => {
    // Clear any user authentication tokens or data from local storage
    localStorage.clear(); // clearing the local storage
    // Redirect the user to the login page or any other desired action
    navigator('/'); // Redirect to the login page
  };
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/home">
          Home
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
       
      </div>
      <form className="container-fluid justify-content-start">
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav">
            <li className="nav-item">
              <Link className="nav-link mx-2" to="/new-request">
                NEW REQUEST
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/my-request">
                MY REQUEST
              </Link>
            </li>
          </ul>
        </div>
      </form>
      <div className="collapse navbar-collapse mx-3" id="navbarNav">
        <div className="nav-link" tabIndex={0}  onClick={handleLogout}>
          LOGOUT
        </div>
      </div>
    </nav>
  );
};

export default Navigation;
