import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import "../styles/Login.css";
import background from "../assets/backgroundhome.jpg";
const Login = () => {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    email: "",
    password: ""
  });

  const [error, setError] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const response = await fetch("https://marketplace-backend-upn5.onrender.com/api/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(form)
      });

      if (response.status === 401 || response.status === 404) {
        navigate("/signup");
        return;
      }

      if (!response.ok) {
        setError("Login failed. Try again.");
        return;
      }

      
      const user = await response.json();
      localStorage.setItem("user", JSON.stringify(user));

      if (user.role === "ADMIN") {
        navigate("/agents");
      } 
      else if (user.role === "AGENT") {
        navigate("/properties");
      } 
      else {
        // BUYER
        navigate("/properties");
      }

    } catch (err) {
      console.error(err);
      setError("Server error. Try again later.");
    }
  };

  return (
  <div className="login-page">
    <img src={background} alt="bg" className="bg-image" />

    <div className="auth-container">
      <h2>Login</h2>

      {error && <p className="error">{error}</p>}

      <form onSubmit={handleLogin}>
        <input
          type="email"
          placeholder="Email"
          value={form.email}
          onChange={(e) =>
            setForm({ ...form, email: e.target.value })
          }
          required
        />

        <input
          type="password"
          placeholder="Password"
          value={form.password}
          onChange={(e) =>
            setForm({ ...form, password: e.target.value })
          }
          required
        />

        <button type="submit">Login</button>
      </form>

      <p className="signup-text">
        New user? <Link to="/signup">Create account</Link>
      </p>
    </div>
  </div>
);

};

export default Login;
