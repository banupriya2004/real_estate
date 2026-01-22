import React, { useEffect, useState } from "react";
import axios from "axios";

const AgentDashboard = () => {
  const [mappings, setMappings] = useState([]);

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"));

    if (!user) {
      console.warn("No user logged in");
      return;
    }

    if (user.role !== "AGENT") {
      console.warn("User is not an AGENT");
      return;
    }

    axios
      .get(`https://marketplace-backend-upn5.onrender.com/api/mappings/agent/user/${user.id}`)
      .then((res) => {
        setMappings(res.data);
      })
      .catch((err) => {
        console.error("Error fetching mappings", err);
      });
  }, []);

  return (
    <div className="container mt-4">
      <h2>Agent Dashboard</h2>

      {mappings.length === 0 ? (
        <div className="alert alert-info">No buyers assigned yet.</div>
      ) : (
        <div className="row">
          {mappings.map((m) => (
            <div key={m.id} className="col-md-4 mb-3">
              <div className="card shadow-sm">
                <div className="card-body">
                  <h5 className="card-title text-primary">
                    {m.property?.title || "Property Title"}
                  </h5>
                  <p className="card-text">
                    <strong>Location:</strong> {m.property?.location}
                  </p>
                  <p className="card-text">
                    <strong>Price:</strong> ${m.property?.price}
                  </p>
                  <hr />

                  <h6 className="text-secondary">Buyer Details</h6>
                  {/* ✅ PERMANENT FIX: Reads transient fields safely */}
                  <p className="mb-1"><strong>Name:</strong> {m.buyerName || "Unknown"}</p>
                  <p className="mb-1"><strong>Email:</strong> {m.buyerEmail || "N/A"}</p>
                  <p className="mb-1"><strong>Phone:</strong> {m.buyerPhone || "N/A"}</p>

                  <hr />
                  <p>
                    <strong>Status:</strong>{" "}
                    <span className="badge bg-success ms-2">{m.status}</span>
                  </p>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default AgentDashboard;