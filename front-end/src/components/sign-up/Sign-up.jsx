import React, { useState } from "react";
import Back from "../common/back/Back";
import './Sign-up.css';
import { Link } from 'react-router-dom';

import user_icon from '../../assets/person.png';
import email_icon from '../../assets/email.png';
import password_icon from '../../assets/password.png';
import { FormCreateStudent } from "../admin-dashboaerd/Students/CreateNewStudent";
import axios from "axios";



export function LOGIN() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
             await axios.post("http://localhost:9090/lll", {
                email,
                password,
            });
            
            // Handle successful login, e.g., redirect to dashboard
            setError("");
        } catch (error) {
            console.error("Login failed:", error);
            setError("Invalid email or password.");
        }
    };

    return (
        <>
            <Back title="LOG IN" />
            <div className="Container">
                <div className="Header">
                    <div className="Text">LOG IN</div>
                    <div className="underline"></div>
                </div>
                <div>
                    <form onSubmit={handleSubmit}>
                        <div className="mb-3">
                            <label htmlFor="email" className="form-label">
                                Email
                            </label>
                            <input
                                type="email"
                                className="form-control"
                                id="email"
                                placeholder="Enter email"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="password" className="form-label">
                                Password
                            </label>
                            <input
                                type="password"
                                className="form-control"
                                id="password"
                                placeholder="Password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                            />
                        </div>
                        <div className="forgot-password">
                        Forgot password? <span>Click Here!</span>
                    </div>

                    <div className="create-account-link">
                        Don't have an account? <Link to="/sign-up">Create One Here</Link>
                    </div>

                        <button type="submit" className="btn btn-primary">
                            Login
                        </button>
                    </form>
                    {error && <div className="alert alert-danger">{error}</div>}{" "}
                    {/* Display error message */}
                </div>
            </div>
        </>
    );
}



/*
export function LOGIN() {
    const [role, setRole] = useState('Student');

  const toggleRole = () => {
    setRole(role === 'Student' ? 'Instructor' : 'Student');
  };
    return (
        <>
            <Back title='LOG IN' />
            <div className="Container">
                <div className="Header">
                    <div className="Text">LOG IN</div>
                    <div className="underline"></div>
                    <div className={`btn ${role === 'Instructor' ? 'btn-primary' : 'btn-secondary'}`}  onClick={toggleRole}>
        {role}
      </div>
                </div>
                <div className="inputs">
                    <div className="input">
                        <img src={email_icon} alt="" />
                        <input type="email" placeholder="Email" />
                    </div>

                    <div className="input">
                        <img src={password_icon} alt="" />
                        <input type="password" placeholder="Password" />
                    </div>

                    <div className="forgot-password">
                        Forgot password? <span>Click Here!</span>
                    </div>

                    <div className="create-account-link">
                        Don't have an account? <Link to="/sign-up">Create One Here</Link>
                    </div>

                    <div className="Login">Login</div>
                </div>
            </div>
        </>
    );
}
*/
const SIGNUP = () => {
    const [action, setAction] = useState("Sign Up");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [dateOfBirth, setDateOfBirth] = useState("");
    const [gender, setGender] = useState("");
    const [emailError, setEmailError] = useState("");
    const [passwordError, setPasswordError] = useState("");
    const [phoneError, setPhoneError] = useState("");
    const [displayErrors, setDisplayErrors] = useState(false);
    const [userType, setUserType] = useState("");

    const handleSignup = () => {
        setDisplayErrors(true);
    
        if (firstName.trim() === "") {
            return;
        }
    
        if (lastName.trim() === "") {
            return;
        }
    
        // Date of Birth validation (you can add your own rules)
        if (dateOfBirth === "") {
            return;
        }
    
        // Gender validation (you can add your own rules)
        if (gender === "") {
            return;
        }
    
        // Email validation
        if (!email.includes("@") || !email.includes(".com")) {
            setEmailError("Invalid email format (e.g., user@example.com)");
            return;
        } else {
            setEmailError("");
        }
    
        // Password validation
        if (password.length < 8) {
            setPasswordError("Password must be at least 8 characters");
            return;
        } else {
            setPasswordError("");
        }
    
        // Phone number validation (add your validation rules)
        if (!phoneNumber.match(/^\d{11}$/)) {
            setPhoneError("Invalid phone number (11 digits required)");
            return;
        } else {
            setPhoneError("");
        }

        if (userType === "") {
            console.error("Please select a user type");
            return;
        }
    
        // If email, password, and phone number are valid, proceed with signup
        console.log("First Name:", firstName);
        console.log("Last Name:", lastName);
        console.log("Email:", email);
        console.log("Password:", password);
        console.log("Phone Number:", phoneNumber);
        console.log("Date of Birth:", dateOfBirth);
        console.log("Gender:", gender);
        console.log("User Type:", userType);
    };    

    const canSwitchToLogin = () => {
        if (action === "Sign Up") {
            // Check if all required fields are filled and error-free
            return (
                firstName.trim() !== "" &&
                lastName.trim() !== "" &&
                email.trim() !== "" &&
                password.trim() !== "" &&
                email.includes("@") &&
                email.includes(".com") &&
                password.length >= 8 &&
                emailError === "" &&
                passwordError === "" &&
                phoneError === "" &&
                (userType === "student" || userType === "instructor")
            );
        }
        return (
        email.trim() !== "" &&
        password.trim() !== "" &&
        email.includes("@") &&
        email.includes(".com") &&
        password.length >= 8 &&
        emailError === "" &&
        passwordError === "" &&
        phoneError === "" &&
        (userType === "student" || userType === "instructor")
    );
    };

    const handleLogin = () => {
        setDisplayErrors(true);

        if (!canSwitchToLogin()) {
            console.error("Please complete the required fields in the sign-up form");
            return;
        }

        window.location.href = "/LOGIN";
    };

    return (
        <>
            <Back title='SIGN UP' />
            <div className="Container">
                <div className="Header">
                    <div className="Text">Sign Up</div>
                    <div className="underline"></div>
                </div>
                
                <FormCreateStudent state="Sign Up"/>
                <br />
                <div className="submit-container">
                    <button className="btn " style={{ background: "#1eb2a6" }}>
                    <Link to="/login"  > Login</Link></button>
                </div>
            </div>
        </>
    );
};

export default SIGNUP;
