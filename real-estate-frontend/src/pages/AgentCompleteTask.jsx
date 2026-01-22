import React, { useState } from "react";
import "../styles/AgentCompleteTask.css";

const AgentCompleteTask = () => {
  const [mappingId, setMappingId] = useState("");
  const [message, setMessage] = useState("");

  const handleComplete = async () => {
    if (!mappingId) {
      setMessage("Please enter mapping ID");
      return;
    }

    try {
      const response = await fetch("https://marketplace-backend-upn5.onrender.com/api/complete-deal", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ mappingId: Number(mappingId) }),
      });

      const data = await response.json();

      if (response.ok) {
        setMessage("✅ Task completed successfully");
        setMappingId("");
      } else {
        setMessage(`❌ Failed: ${data.error || "Unknown error"}`);
      }
    } catch (error) {
      console.error(error);
      setMessage("⚠ Server error");
    }
  };

  return (
    <div className="agent-container">
      <h2>Complete Buyer Task</h2>

      <input
        type="number"
        placeholder="Enter Deal Mapping ID"
        value={mappingId}
        onChange={(e) => setMappingId(e.target.value)}
      />

      <button onClick={handleComplete}>Mark as Completed</button>

      <p className="message">{message}</p>
    </div>
  );
};

export default AgentCompleteTask;
