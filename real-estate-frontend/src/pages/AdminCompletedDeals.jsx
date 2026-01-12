import React, { useState, useEffect } from "react";
import "../styles/AdminCompletedDeals.css";

const AdminCompletedDeals = () => {
  const [deals, setDeals] = useState([]);

  const fetchDeals = async () => {
    const response = await fetch(
      "http://localhost:8080/api/admin/completed-deals"
    );
    const data = await response.json();
    setDeals(data);
  };

  const deleteDeal = async (id) => {
    await fetch(
      `http://localhost:8080/api/admin/completed-deals/${id}`,
      { method: "DELETE" }
    );
    fetchDeals();
  };

  useEffect(() => {
    fetchDeals();
  }, []);

  return (
    <div className="admin-container">
      <h2>Completed Deals</h2>

      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Buyer ID</th>
            <th>Location</th>
            <th>Agent</th>
            <th>Action</th>
          </tr>
        </thead>

        <tbody>
          {deals.map((deal) => (
            <tr key={deal.id}>
              <td>{deal.id}</td>
              <td>{deal.buyerId}</td>
              <td>{deal.location}</td>
              <td>{deal.agent?.user?.name}</td>
              <td>
                <button onClick={() => deleteDeal(deal.id)}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AdminCompletedDeals;
