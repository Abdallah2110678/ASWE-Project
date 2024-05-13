import React, { useState } from "react";
import Back from "../common/back/Back";
import axios from "axios";
import { REST_API_BASE_URL } from "./../../App";
import "./userProfile.css";

const UserProfile = () => {
  const [user, setUser] = useState({
    name: "",
    email: "",
    profilePicture: "https://example.com/profile.jpg",
  });
  const [successMessage, setSuccessMessage] = useState("");
  const [errors, setErrors] = useState({});

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    let errorMessage = "";
    if (!value.trim()) {
      errorMessage = `${name} is required.`;
    }

    setErrors((prevErrors) => ({
      ...prevErrors,
      [name]: errorMessage,
    }));

    setUser((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleFormSubmit = async (e) => {
    e.preventDefault();
    const hasErrors = Object.values(errors).some((error) => error !== "");
    if (hasErrors) {
      return;
    }

    try {
      const response = await axios.put(
        `${REST_API_BASE_URL}/user/profile`,
        user
      );
      console.log("Profile updated:", response.data);
      setSuccessMessage("Profile updated successfully.");
    } catch (error) {
      console.error("Profile update failed:", error);
    }
  };

  return (
    <>
      <Back title="User Profile" />
      <div className="user-profile-container">
        <div className="Header">
          <div className="Text">User Profile</div>
          <div className="underline"></div>
        </div>
        <form onSubmit={handleFormSubmit}>
          <div className="mb-3">
            <label htmlFor="name" className="form-label">
              Name:
            </label>
            <input
              type="text"
              className="form-control"
              id="name"
              name="name"
              value={user.name}
              onChange={handleInputChange}
            />
            {errors.name && (
              <div className="error text-danger">{errors.name}</div>
            )}
          </div>

          <div className="mb-3">
            <label htmlFor="email" className="form-label">
              Email:
            </label>
            <input
              type="email"
              className="form-control"
              id="email"
              name="email"
              value={user.email}
              onChange={handleInputChange}
            />
            {errors.email && (
              <div className="error text-danger">{errors.email}</div>
            )}
          </div>

          <button type="submit" className="btn btn-primary">
            Update Profile
          </button>
          {successMessage && (
            <div className="alert alert-success mt-3">{successMessage}</div>
          )}
        </form>
      </div>
    </>
  );
};

export default UserProfile;
