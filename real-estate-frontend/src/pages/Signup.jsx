import React, { useState } from "react";
import "../styles/Signup.css";
import { useNavigate } from "react-router-dom";

const Signup = () => {
  const navigate = useNavigate();

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
    role: "BUYER",
    mobileNumber: ""
  });

  const [error, setError] = useState("");

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const handleSignup = async (e) => {
    e.preventDefault();
    setError("");

    if (!emailRegex.test(form.email)) {
      setError("Please enter a valid email address (e.g., user@gmail.com)");
      return;
    }

    console.log("Sending data:", form);

    try {
      const response = await fetch("https://marketplace-backend-upn5.onrender.com/api/auth/signup", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(form)
      });

      const data = await response.json();

      if (!response.ok) {
        setError(data.message || "Signup failed");
        return;
      }

      navigate("/login");

    } catch (err) {
      console.error("Signup error:", err);
      setError("Server error. Try again later.");
    }
  };

  return (
    // ✅ ADDED WRAPPER FOR CENTERING
    <div className="signup-page">
      <div className="signup-container">
        <h2>Create Account</h2>

        {error && <p className="error">{error}</p>}

        <form onSubmit={handleSignup}>
          <input
            type="text"
            name="name"
            placeholder="Name"
            value={form.name}
            onChange={handleChange}
            required
          />

          <input
            type="email"
            name="email"
            placeholder="Email"
            value={form.email}
            onChange={(e) => {
              handleChange(e);
              if (!emailRegex.test(e.target.value)) {
                setError("Invalid email format");
              } else {
                setError("");
              }
            }}
            required
          />

          <input
            type="password"
            name="password"
            placeholder="Password"
            value={form.password}
            onChange={handleChange}
            required
          />

          <input
            type="tel"
            name="mobileNumber"
            placeholder="Mobile Number"
            value={form.mobileNumber} 
            onChange={handleChange}
            pattern="[0-9]{10}"
            title="Enter 10 digit phone number"
            required
          />

          <select
            name="role"
            value={form.role}
            onChange={handleChange}
          >
            <option value="BUYER">Buyer</option>
            <option value="AGENT">Agent</option>
            {/* <option value="ADMIN">Admin</option> */}
          </select>

          <button type="submit">Signup</button>
        </form>
      </div>
    </div>
  );
};

export default Signup;