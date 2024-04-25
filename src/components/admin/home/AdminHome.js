import React from "react";
import "./AdminHome.css";
import AdminNavbar from '../navbar/AdminNavbar' 
export default function Home() {
  return (
    <>
    <AdminNavbar/>
    <div className="home">
      <div>
        <h1 id="wel">WELCOME</h1>
        <h2 id="en">WARDENS</h2>
      </div>
    </div>
    </>
  );
}
