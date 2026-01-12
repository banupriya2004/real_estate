import React, { useEffect, useState } from "react";

const BuyerDashboard = () => {
  const [agents, setAgents] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user")); // buyer info
    if (!user) return;

    const fetchAssignedAgents = async () => {
      try {
        const res = await fetch(`http://localhost:8080/api/mappings/buyer/${user.id}/agents`);
        if (!res.ok) throw new Error("Failed to fetch agents");
        const data = await res.json();
        setAgents(data);
      } catch (err) {
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    fetchAssignedAgents();
  }, []);

  if (loading) return <p>Loading...</p>;

  return (
    <div>
      <h2>Buyer Dashboard</h2>
      {agents.length > 0 ? (
        agents.map(agent => (
          <div key={agent.id} style={{border: "1px solid #ccc", padding: "10px", margin: "10px 0"}}>
            <h3>Assigned Agent:</h3>
            <p>Name: {agent.name}</p>
            <p>Email: {agent.email}</p>
            <p>Location: {agent.location}</p>
            <p>Phone: {agent.phone}</p>
          </div>
        ))
      ) : (
        <p>No agent assigned yet.</p>
      )}
    </div>
  );
};

export default BuyerDashboard;
