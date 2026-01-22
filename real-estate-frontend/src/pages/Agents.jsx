import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/Agents.css";

function Agents() {
  const [agents, setAgents] = useState([]);
  const navigate = useNavigate();

  // admin check (simple)
  const isAdmin = localStorage.getItem("role") === "ADMIN";

  useEffect(() => {
    fetch("https://marketplace-backend-upn5.onrender.com/api/agents")
      .then(res => res.json())
      .then(data => setAgents(data));
  }, []);

  const handleDelete = async (id) => {
    if (!window.confirm("Delete this agent?")) return;

    await fetch(`https://marketplace-backend-upn5.onrender.com/api/agents/${id}`, {
      method: "DELETE",
    });

    setAgents(agents.filter(a => a.id !== id));
  };

  return (
    <div className="agents-page">
      <div className="agents-card">

        {/* HEADER */}
        <div className="agents-header">
          <h2>Agent Listing</h2>

          {isAdmin && (
            <button
              className="add-btn"
              onClick={() => navigate("/add-agent")}
            >
              + Add Agent
            </button>
          )}
        </div>

        {/* TABLE */}
        <table className="agent-table">
          <thead>
            <tr>
              <th>Name</th>
              <th>Area</th>
              <th>Experience</th>
              <th>Rating</th>
              <th>Phone Number</th>
              {isAdmin && <th>Actions</th>}
            </tr>
          </thead>

          <tbody>
            {agents.map(agent => (
              <tr key={agent.id}>
                <td>{agent.name}</td>
                <td>{agent.area}</td>
                <td>{agent.experience} yrs</td>
                <td>{agent.rating}</td>

                {isAdmin && (
                  <td>
                    <button
                      className="edit-btn"
                      onClick={() => navigate(`/edit-agent/${agent.id}`)}
                    >
                      Edit
                    </button>
                    <button
                      className="delete-btn"
                      onClick={() => handleDelete(agent.id)}
                    >
                      Delete
                    </button>
                  </td>
                )}
              </tr>
            ))}
          </tbody>
        </table>

      </div>
    </div>
  );
}

export default Agents;
