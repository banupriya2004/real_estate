import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/Properties.css";

const Properties = () => {
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem("user"));
  const role = user?.role;

  const [properties, setProperties] = useState([]);
  const [filteredProperties, setFilteredProperties] = useState([]);
  
  // Filter States
  const [location, setLocation] = useState("");
  const [type, setType] = useState("");
  const [minPrice, setMinPrice] = useState("");
  const [maxPrice, setMaxPrice] = useState("");

  // 🔹 Fetch properties
  useEffect(() => {
    const fetchProperties = async () => {
      try {
        const res = await fetch("http://localhost:8080/api/properties");
        const data = await res.json();
        setProperties(data);
        setFilteredProperties(data);
      } catch (err) {
        console.error("Error fetching properties:", err);
      }
    };
    fetchProperties();
  }, []);

  // 🔹 Search filter
  const handleSearch = () => {
    let result = properties;

    if (location) {
      result = result.filter((p) =>
        p.location.toLowerCase().includes(location.toLowerCase())
      );
    }
    if (type) {
      result = result.filter((p) => p.type === type);
    }
    if (minPrice) {
      result = result.filter((p) => p.price >= Number(minPrice));
    }
    if (maxPrice) {
      result = result.filter((p) => p.price <= Number(maxPrice));
    }

    setFilteredProperties(result);
  };

  // 🔹 DELETE PROPERTY (ADMIN)
  const handleDelete = async (id) => {
    if (!window.confirm("Delete this property?")) return;

    try {
      await fetch(`http://localhost:8080/api/properties/${id}`, {
        method: "DELETE",
      });

      setProperties((prev) => prev.filter((p) => p.id !== id));
      setFilteredProperties((prev) => prev.filter((p) => p.id !== id));
      alert("Property deleted");
    } catch (err) {
      alert("Delete failed");
    }
  };

  // 🔹 BUY NOW (BUYER ONLY)
  const handleBuyNow = async (propertyId) => {
    if (!user) {
      alert("Please login first");
      navigate("/login");
      return;
    }

    try {
      // Send request even if agent is null. Backend should handle "null" agent or auto-assign.
      const res = await fetch(
        `http://localhost:8080/api/mappings/assign?buyerId=${user.id}&propertyId=${propertyId}`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
        }
      );

      if (!res.ok) {
        const errorMsg = await res.text();
        throw new Error(errorMsg || "Failed to assign agent");
      }

      alert("Request Sent! Property marked for interest.");
      // navigate("/buyer-dashboard"); // Optional redirect
    } catch (err) {
      console.error(err);
      alert("Error: " + err.message);
    }
  };

  return (
    <div className="properties-page">
      {/* 🔍 Search */}
      <div className="search-box">
        <input
          placeholder="Location"
          value={location}
          onChange={(e) => setLocation(e.target.value)}
        />
        <select value={type} onChange={(e) => setType(e.target.value)}>
          <option value="">All Types</option>
          <option value="Apartment">Apartment</option>
          <option value="Villa">Villa</option>
          <option value="Plot">Plot</option>
          <option value="Flat">Flat</option>
          <option value="Duplex">Duplex</option>
        </select>
        <input
          type="number"
          placeholder="Min Price"
          value={minPrice}
          onChange={(e) => setMinPrice(e.target.value)}
        />
        <input
          type="number"
          placeholder="Max Price"
          value={maxPrice}
          onChange={(e) => setMaxPrice(e.target.value)}
        />
        <button onClick={handleSearch}>Search</button>
      </div>

      {/* Header */}
      <div className="header">
        <h2>Properties</h2>
        {/* Only Agents or Admins typically add properties */}
        {(role === "ADMIN" || role === "AGENT") && (
          <button
            className="addprop"
            onClick={() => navigate("/add-property")}
          >
            + Add Property
          </button>
        )}
      </div>

      {/* Property Cards */}
      <div className="property-grid">
        {filteredProperties.map((property) => (
          <div className="property-card" key={property.id}>
            <img
              src={`http://localhost:8080/uploads/${property.imageUrls}`}
              alt={property.title}
              onError={(e) => (e.target.style.display = "none")} 
              style={{ width: "100%", height: "180px", objectFit: "cover" }}
            />

            <h3>{property.title}</h3>
            <p><strong>Location:</strong> {property.location}</p>
            <p className="price">₹ {property.price}</p>
            <p><strong>Type:</strong> {property.type}</p>
            
            {/* Show Agent Name or 'Unassigned' */}
            <p style={{ fontSize: "0.9em", color: "#666" }}>
                Agent: {property.agent ? property.agent.name : "Pending Assignment"}
            </p>

            {/* 🔐 ADMIN ACTIONS */}
            {role === "ADMIN" && (
              <div className="admin-actions">
                <button
                  className="edit-btn"
                  onClick={() => navigate(`/edit-property/${property.id}`)}
                >
                  Edit
                </button>
                <button
                  className="delete-btn"
                  onClick={() => handleDelete(property.id)}
                >
                  Delete
                </button>
              </div>
            )}

            {/* 🛒 BUY NOW BUTTON - ✅ ENABLED FOR ALL */}
            {role === "BUYER" && (
              <button
                className="buy-btn"
                onClick={() => handleBuyNow(property.id)}
                // REMOVED the "disabled" check so you can buy anything
              >
                Buy Now
              </button>
            )}
          </div>
        ))}
      </div>
    </div>
  );
};

export default Properties;