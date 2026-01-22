// import React, { useState } from "react";
// import "../styles/AddProperty.css";

// const AddProperty = () => {
//   const [property, setProperty] = useState({
//     title: "",
//     location: "",
//     price: "",
//     type: "",
//     description: "",
//     image: null
//   });

//   const handleChange = (e) => {
//     setProperty({ ...property, [e.target.name]: e.target.value });
//   };

//   const handleImageChange = (e) => {
//     setProperty({ ...property, image: e.target.files[0] });
//   };

//   const handleSubmit = async (e) => {
//   e.preventDefault();

//   const formData = new FormData();
//   formData.append("title", title);           // your state variables
//   formData.append("location", location);
//   formData.append("price", price);
//   formData.append("type", type);
//   formData.append("description", description);
//   formData.append("agentId", parseInt(agentId)); // converts string to number
//      // number
//   formData.append("image", imageFile);      // file from <input type="file">

//   try {
//     const response = await fetch("http://localhost:8080/api/properties/add", {
//       method: "POST",
//       body: formData
//       // Do NOT set Content-Type manually! Browser will set it to multipart/form-data
//     });

//     if (!response.ok) {
//       throw new Error("Server error: " + response.statusText);
//     }

//     const data = await response.json();
//     console.log("Property added:", data);
//     alert("Property added successfully!");
//   } catch (error) {
//     console.error("Error submitting property:", error);
//     alert("Failed to add property. See console for details.");
//   }
// };


//   return (
//     <div className="add-property-container">
//       <form className="add-property-form" onSubmit={handleSubmit}>
//         <h2>Add Property</h2>

//         <input
//           type="text"
//           name="title"
//           placeholder="Property Title"
//           value={property.title}
//           onChange={handleChange}
//           required
//         />

//         <input
//           type="text"
//           name="location"
//           placeholder="Location"
//           value={property.location}
//           onChange={handleChange}
//           required
//         />

//         <input
//           type="number"
//           name="price"
//           placeholder="Price"
//           value={property.price}
//           onChange={handleChange}
//           required
//         />

//         <select
//           name="type"
//           value={property.type}
//           onChange={handleChange}
//           required
//         >
//           <option value="">Select Property Type</option>
//           <option value="Flat">Flat</option>
//           <option value="Villa">Villa</option>
//           <option value="Plot">Plot</option>
//         </select>

//         <textarea
//           name="description"
//           placeholder="Property Description"
//           value={property.description}
//           onChange={handleChange}
//           rows="4"
//         />

//        <input
//   type="file"
//   onChange={(e) => setImageFile(e.target.files[0])}
// />


//         <button type="submit">Add Property</button>
//       </form>
//     </div>
//   );
// };

// export default AddProperty;






import React, { useState } from "react";
import "../styles/AddProperty.css";

const AddProperty = () => {

  const handleSubmit = async (e) => {
    e.preventDefault();

    // ✅ automatic mapping
    const formData = new FormData(e.target);

    try {
      const response = await fetch("https://marketplace-backend-upn5.onrender.com/api/properties/add", {
        method: "POST",
        body: formData
      });

      if (!response.ok) {
        throw new Error("Server error");
      }

      const data = await response.json();
      alert("Property added successfully!");
      console.log(data);

      e.target.reset(); // clear form

    } catch (error) {
      console.error("Upload failed:", error);
      alert("Failed to add property");
    }
  };

  return (
    <div className="add-property-container">
      <form className="add-property-form" onSubmit={handleSubmit}>

        <h2>Add Property</h2>

        <input type="text" name="title" placeholder="Property Title" required />

        <input type="text" name="location" placeholder="Location" required />

        <input type="number" name="price" placeholder="Price" required />

        <select name="type" required>
          <option value="">Select Property Type</option>
          <option value="Flat">Flat</option>
          <option value="Villa">Villa</option>
          <option value="Plot">Plot</option>
        </select>

        <textarea
          name="description"
          placeholder="Property Description"
          rows="4"
        />

        {/* IMAGE UPLOAD */}
        <input
          type="file"
          name="image"
          accept="image/*"
          required
        />

        <button type="submit">Add Property</button>

      </form>
    </div>
  );
};

export default AddProperty;
