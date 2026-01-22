import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Navbar.css";
import profileImg from "../assets/person-profile.png"; // ✅ CORRECT IMPORT

const Navbar = () => {
  const [isOpen, setIsOpen] = useState(true);
  const [showMenu, setShowMenu] = useState(false);
  const navigate = useNavigate();

  const user = JSON.parse(localStorage.getItem("user"));

  const toggleSidebar = () => setIsOpen(!isOpen);

  const handleLogout = () => {
    localStorage.clear();
    navigate("/");
  };
  const role = user?.role;


  return (
    <>
      {/* Hamburger */}
      <div className="hamburger" onClick={toggleSidebar}>
        &#9776;
      </div>
      {/* Sidebar */}
      <div className={`sidebar ${isOpen ? "open" : ""}`}>
        <div className="logo">🏠 RealEstate</div>

        <ul className="menu">

  <li><Link to="/" onClick={toggleSidebar}>Login</Link></li>

  {role === "BUYER" && (
    <>
      <li><Link to="/properties">Properties</Link></li>
      <li><Link to="/agents">Agents</Link></li>
       <li><Link to="/buyer-dashboard">My Dashboard</Link></li>
      <li><Link to="/match-agent">Match Agent</Link></li>
    </>
  )}

  {/* AGENT MENU */}
          {role === "AGENT" && (
            <>
              <li><Link to="/agent-dashboard" onClick={toggleSidebar}>My Dashboard</Link></li>
              <li><Link to="/complete-task" onClick={toggleSidebar}>Completed Buyers</Link></li>
            </>
          )}


  {role === "ADMIN" && (
    <>
      {/* <li><Link to="/admin-dashboard">Dashboard</Link></li> */}
      <li><Link to="/properties">Properties</Link></li>
      <li><Link to="/agents">Agents</Link></li>
      <li><Link to="/add-agent">Add Agent</Link></li>
      <li><Link to="/add-property">Add Property</Link></li>
      <li><Link to="/completed-deals">Completed Deals</Link></li>
    </>
  )}

  {user && (
    <li><Link to="/profile">Profile</Link></li>
  )}

</ul>


        {user && (
          <div className="logout-container">
            <button className="logout-btn" onClick={handleLogout}>
              Logout
            </button>
          </div>
        )}
      </div>

      {/* Top Navbar */}
      <div className="main-content">
      <div style={styles.nav}>
        <span onClick={() => navigate("/agents")}></span>

        
          <div className="profile-wrapper">
            <div
              className="avatar"
              onClick={() => setShowMenu(!showMenu)}
            >
              <img
                src={profileImg}
                alt="Profile"
                className="avatar-img"
              />
            </div>

            {showMenu && (
              <div className="dropdown">
                <div onClick={() => navigate("/profile")}>Profile</div>
                <div onClick={handleLogout}>Logout</div>
              </div>
            )}
          </div>
      </div>
      </div>
    </>
  );
};

const styles = {
  nav: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    padding: "15px",
    background: "#0d2742",
    color: "white"
  }
};

export default Navbar;
