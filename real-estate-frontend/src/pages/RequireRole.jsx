import { Navigate, Outlet } from "react-router-dom";

const RequireRole = ({ allowedRoles = [] }) => {
  // Get user from localStorage
  const user = JSON.parse(localStorage.getItem("user"));

  // 1️⃣ If user not logged in, redirect to login
  if (!user || !user.role) {
    return <Navigate to="/" replace />;
  }

  // 2️⃣ If user role is not allowed, redirect to unauthorized
  if (!allowedRoles.includes(user.role)) {
    return <Navigate to="/unauthorized" replace />;
  }

  // 3️⃣ Access granted, render the nested route
  return <Outlet />;
};

export default RequireRole;
