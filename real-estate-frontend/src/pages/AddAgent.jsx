import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/AddAgent.css";

function AddAgent() {
  const [agent, setAgent] = useState({
  name: "",
  email: "",
  phone: "",
  location: "",
  available: false
});


  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    await fetch("https://marketplace-backend-upn5.onrender.com/api/agents", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(agent),
    });

    navigate("/agents"); 
  };

  return (
    <div style={{ display: "flex", justifyContent: "center", marginTop: "40px" }}>
      <form onSubmit={handleSubmit} style={{ width: "400px" }}>
        <h3>Add Agent</h3>

        <input placeholder="Name" onChange={e => setAgent({...agent, name: e.target.value})} />
        <input placeholder="Area" onChange={e => setAgent({...agent, area: e.target.value})} />
        <input placeholder="Email" onChange={e => setAgent({ ...agent, email: e.target.value })}/>
        <input placeholder="Phone Number" onChange={e => setAgent({ ...agent, phone: e.target.value })}/>
        <input placeholder="Experience" onChange={e => setAgent({...agent, experience: e.target.value})} />
        <input placeholder="Rating" onChange={e => setAgent({...agent, rating: e.target.value})} />

        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default AddAgent;
