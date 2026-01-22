import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import "../styles/Properties.css";

const EditProperty = () => {
  const navigate = useNavigate();
  const { id } = useParams(); // Property ID from URL
  const role = JSON.parse(localStorage.getItem("user"))?.role;

  const [property, setProperty] = useState({
    title: "",
    location: "",
    type: "",
    price: "",
    imageUrls: ""
  });

  // 🔹 Load property by ID
  useEffect(() => {
    const fetchProperty = async () => {
      try {
        const res = await fetch(`https://marketplace-backend-upn5.onrender.com/api/properties/${id}`);
        const data = await res.json();
        setProperty({
          title: data.title,
          location: data.location,
          type: data.type,
          price: data.price,
          imageUrls: data.imageUrls || ""
        });
      } catch (err) {
        console.error(err);
        alert("Failed to load property");
      }
    };
    fetchProperty();
  }, [id]);

  // 🔹 Handle input changes
  const handleChange = (e) => {
    const { name, value } = e.target;
    setProperty(prev => ({ ...prev, [name]: value }));
  };

  // 🔹 Update property
  const handleSubmit = async (e) => {
    e.preventDefault();
    if (role !== "ADMIN") {
      alert("Access denied");
      return;
    }

    try {
      await fetch(`https://marketplace-backend-upn5.onrender.com/api/properties/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(property)
      });
      alert("Property updated successfully");
      navigate("/properties"); // Back to properties list
    } catch (err) {
      console.error(err);
      alert("Update failed");
    }
  };

  return (
    <div className="properties-page">
      <h2>Edit Property</h2>

      <form className="property-form" onSubmit={handleSubmit}>

        <input
          type="text"
          name="title"
          placeholder="Title"
          value={property.title}
          onChange={handleChange}
          required
        />

        <input
          type="text"
          name="location"
          placeholder="Location"
          value={property.location}
          onChange={handleChange}
          required
        />

        <select name="type" value={property.type} onChange={handleChange} required>
          <option value="">Select Type</option>
          <option value="Flat">Flat</option>
          <option value="Villa">Villa</option>
          <option value="Plot">Plot</option>
        </select>

        <input
          type="number"
          name="price"
          placeholder="Price"
          value={property.price}
          onChange={handleChange}
          required
        />

        <input
          type="text"
          name="imageUrls"
          placeholder="Image filename (optional)"
          value={property.imageUrls}
          onChange={handleChange}
        />

        <button type="submit" className="addprop">Update Property</button>
        <button type="button" onClick={() => navigate("/properties")}>Cancel</button>
      </form>
    </div>
  );
};

export default EditProperty;
