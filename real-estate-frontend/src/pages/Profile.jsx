import React from "react";

function Profile() {
  const user = JSON.parse(localStorage.getItem("user"));

  if (!user) return <h3>Please login</h3>;

  return (
    <div style={styles.card}>
      <h2>Profile</h2>

      <p><b>Name:</b> {user.name}</p>
      <p><b>Email:</b> {user.email}</p>
      <p><b>Role:</b> {user.role}</p>

      {user.role === "ADMIN" && (
        <div style={styles.adminBox}>
          <h4>Admin Controls</h4>
          <p>✔ Manage Agents</p>
          <p>✔ Add / Edit / Delete</p>
        </div>
      )}
    </div>
  );
}

const styles = {
  card: {
    width: "400px",
    margin: "50px auto",
    padding: "20px",
    background: "#f9fafb",
    borderRadius: "8px"
  },
  adminBox: {
    marginTop: "15px",
    background: "#e0f2fe",
    padding: "10px",
    borderRadius: "6px"
  }
};

export default Profile;
