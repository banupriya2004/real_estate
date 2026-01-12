import React from "react";

import { BrowserRouter, Routes, Route, Navigate, useLocation } from "react-router-dom";
import Navbar from "./components/Navbar";

import EditProperty from "./pages/EditProperty";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Properties from "./pages/Properties";
import AddProperty from "./pages/AddProperty";
import Agents from "./pages/Agents";
import AddAgent from "./pages/AddAgent";
import MatchAgent from "./pages/MatchAgent";
import Profile from "./pages/Profile";
import AgentCompleteTask from "./pages/AgentCompleteTask";
import AdminCompletedDeals from "./pages/AdminCompletedDeals";
import AgentDashboard from "./pages/AgentDashboard";
import BuyerDashboard from "./pages/BuyerDashboard";



function App() {
  const location = useLocation();
  const user = JSON.parse(localStorage.getItem("user"));
  const role = user?.role;

  const hideNavbar =
    location.pathname === "/" ||
    location.pathname === "/signup";

  // 🔹 Role guard helper
      

const RequireRole = ({ children, allowedRoles }) => {
  const user = JSON.parse(localStorage.getItem("user"));

  console.log("RequireRole check:", { user, allowedRoles });

  if (!user) {
    return <Navigate to="/" replace />;
  }

  if (!Array.isArray(allowedRoles)) {
    console.error("allowedRoles is NOT an array", allowedRoles);
    return <Navigate to="/" replace />;
  }

  if (!allowedRoles.includes(user.role)) {
    return <Navigate to="/" replace />;
  }

  return children;
};



  return (
    <>
      {!hideNavbar && <Navbar />}

      <div className="main-content">
        <Routes>

          {/* AUTH */}
          <Route path="/" element={<Login />} />
          <Route path="/signup" element={<Signup />} />

          {/* PROTECTED ROUTES */}
          <Route
            path="/properties"
            element={
              <RequireRole allowedRoles={["BUYER", "AGENT", "ADMIN"]}>
                <Properties />
              </RequireRole>
            }
          />

          <Route
            path="/edit-property/:id"
            element={
              <RequireRole allowedRoles={["ADMIN"]}>
                <EditProperty />
              </RequireRole>
            }
          />

          <Route
            path="/add-property"
            element={
              <RequireRole allowedRoles={["ADMIN"]}>
                <AddProperty />
              </RequireRole>
            }
          />

          <Route
            path="/agents"
            element={
              <RequireRole allowedRoles={["BUYER", "ADMIN"]}>
                <Agents />
              </RequireRole>
            }
          />

          <Route
  path="/agent-dashboard"
  element={
    <RequireRole allowedRoles={["AGENT"]}>
      <AgentDashboard />
    </RequireRole>
  }
/>

<Route
  path="/buyer-dashboard"
  element={
    <RequireRole allowedRoles={["BUYER"]}>
      <BuyerDashboard />
    </RequireRole>
  }
/>


          <Route
            path="/add-agent"
            element={
              <RequireRole allowedRoles={["ADMIN"]}>
                <AddAgent />
              </RequireRole>
            }
          />

          <Route
            path="/match-agent"
            element={
              <RequireRole allowedRoles={["BUYER"]}>
                <MatchAgent />
              </RequireRole>
            }
          />

          <Route
            path="/complete-task"
            element={
              <RequireRole allowedRoles={["AGENT"]}>
                <AgentCompleteTask />
              </RequireRole>
            }
          />

          <Route
            path="/completed-deals"
            element={
              <RequireRole allowedRoles={["ADMIN"]}>
                <AdminCompletedDeals />
              </RequireRole>
            }
          />

          <Route
            path="/profile"
            element={
              <RequireRole allowedRoles={["BUYER", "AGENT", "ADMIN"]}>
                <Profile />
              </RequireRole>
            }
          />

          {/* Catch-all */}
          <Route path="*" element={<Navigate to="/" replace />} />

        </Routes>
      </div>
    </>
  );
}

export default App;
