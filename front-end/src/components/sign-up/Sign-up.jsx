import React, { useEffect, useState } from "react";
import Back from "../common/back/Back";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { REST_API_BASE_URL } from "./../../App";

const SignUp = () => {
  const navigate = useNavigate();
  const [fname, setFname] = useState("");
  const [lname, setLname] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [gender, setGender] = useState("");
  const [dob, setDob] = useState("");
  const [phone, setPhone] = useState("");
  const [role, setRole] = useState(""); // Added role state
  const [successMessage, setSuccessMessage] = useState("");
  const [errors, setErrors] = useState({});

  const handleChange = (e) => {
    const { name, value } = e.target;

    // Define validation rules for each field
    const validationRules = {
      fname: "fname",
      lname: "lname",
      email: "email",
      password: "password",
      gender: "gender",
      dob: "dob",
      phone: "phone",
      role: "role", // Added rule for role
    };

    // Perform validation
    let errorMessage = "";
    if (!value.trim()) {
      errorMessage = `${validationRules[name]} is required.`;
    } else if (name === "dob") {
      // Validate date of birth
      const currentDate = new Date();
      const selectedDate = new Date(value);
      const age = currentDate.getFullYear() - selectedDate.getFullYear();
      if (age < 15) {
        errorMessage = "You must be at least 15 years old.";
      }
    }

    // Update state with validation result
    setErrors((prevErrors) => ({
      ...prevErrors,
      [name]: errorMessage,
    }));

    // Update state with user input
    switch (name) {
      case "fname":
        setFname(value);
        break;
      case "lname":
        setLname(value);
        break;
      case "email":
        setEmail(value);
        break;
      case "password":
        setPassword(value);
        break;
      case "gender":
        setGender(value);
        break;
      case "dob":
        setDob(value);
        break;
      case "phone":
        setPhone(value);
        break;
      case "role":
        setRole(value);
        break; // Added case for role
      default:
        break;
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Check if there are any error messages
    const hasErrors = Object.values(errors).some((error) => error !== "");
    if (hasErrors) {
      return; // Prevent form submission if there are errors
    }

    try {
      const response = await axios.post(`${REST_API_BASE_URL}/user/register`, {
        fname,
        lname,
        email,
        password,
        gender,
        dob,
        phone,
        role,
      });
      console.log(response.data);
      setSuccessMessage("Sign up successful.");
      setFname("");
      setLname("");
      setEmail("");
      setPassword("");
      setGender("");
      setDob("");
      setPhone("");
      setRole("");
      navigate("/login");
    } catch (error) {
      console.error("Sign up failed:", error);
    }
  };

  return (
    <>
      <Back title="Sign Up" />
      <div className="Container">
        <div className="Header">
          <div className="Text">Sign Up</div>
          <div className="underline"></div>
        </div>
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="firstName" className="form-label">
              First Name:
            </label>
            <input
              type="text"
              className="form-control"
              id="firstName"
              name="fname"
              value={fname}
              onChange={handleChange}
            />
            {errors.fname && (
              <div className="error text-danger">{errors.fname}</div>
            )}
          </div>
          <div className="mb-3">
            <label htmlFor="lastName" className="form-label">
              Last Name:
            </label>
            <input
              type="text"
              className="form-control"
              id="lastName"
              name="lname"
              value={lname}
              onChange={handleChange}
            />
            {errors.lname && (
              <div className="error text-danger">{errors.lname}</div>
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
              value={email}
              onChange={handleChange}
            />
            {errors.email && (
              <div className="error text-danger">{errors.email}</div>
            )}
          </div>
          <div className="mb-3">
            <label htmlFor="password" className="form-label">
              Password:
            </label>
            <input
              type="password"
              className="form-control"
              id="password"
              name="password"
              value={password}
              onChange={handleChange}
            />
            {errors.password && (
              <div className="error text-danger">{errors.password}</div>
            )}
          </div>
          <div className="mb-3">
            <label htmlFor="gender" className="form-label">
              Gender:
            </label>
            <select
              className="form-select"
              id="gender"
              name="gender"
              value={gender}
              onChange={handleChange}
            >
              <option value="">Select Gender</option>
              <option value="male">Male</option>
              <option value="female">Female</option>
            </select>
            {errors.gender && (
              <div className="error text-danger">{errors.gender}</div>
            )}
          </div>
          <div className="mb-3">
            <label htmlFor="dob" className="form-label">
              Date of Birth:
            </label>
            <input
              type="date"
              className="form-control"
              id="dob"
              name="dob"
              value={dob}
              onChange={handleChange}
            />
            {errors.dob && (
              <div className="error text-danger">{errors.dob}</div>
            )}
          </div>
          <div className="mb-3">
            <label htmlFor="phone" className="form-label">
              Phone:
            </label>
            <input
              type="text"
              className="form-control"
              id="phone"
              name="phone"
              value={phone}
              onChange={handleChange}
            />
            {errors.phone && (
              <div className="error text-danger">{errors.phone}</div>
            )}
          </div>
          {/* New field for role */}
          <div className="mb-3">
            <label htmlFor="role" className="form-label">
              Role:
            </label>
            <select
              className="form-select"
              id="role"
              name="role"
              value={role}
              onChange={handleChange}
            >
              <option value="">Select Role</option>
              <option value="STUDENT">Student</option>
              <option value="INSTRUCTOR">Instructor</option>
            </select>
            {errors.role && (
              <div className="error text-danger">{errors.role}</div>
            )}
          </div>
          {/* Rest of the form */}
          <button type="submit" className="btn btn-primary">
            Sign Up
          </button>
          {successMessage && (
            <div className="alert alert-success mt-3">{successMessage}</div>
          )}
        </form>
      </div>
    </>
  );
};

export default SignUp;
