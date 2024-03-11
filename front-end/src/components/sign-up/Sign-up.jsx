import React, { useState } from "react";
import Back from "../common/back/Back";
import './Sign-up.css';
import { Link } from 'react-router-dom';

import user_icon from '../../assets/person.png';
import email_icon from '../../assets/email.png';
import password_icon from '../../assets/password.png';

export function LOGIN() {
    return (
        <>
            <Back title='LOG IN' />
            <div className="Container">
                <div className="Header">
                    <div className="Text">LOG IN</div>
                    <div className="underline"></div>
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
                <form className="inputs">
                    <div className="input">
                        <img src={user_icon} alt="" />
                        <input type="text" placeholder="First Name" value={firstName} onChange={(e) => setFirstName(e.target.value)} />
                        {displayErrors && firstName.trim() === "" && <div className="field-message">Please enter your First Name</div>}
                    </div>
                    <div className="input">
                        <img src={user_icon} alt="" />
                        <input type="text" placeholder="Last Name" value={lastName} onChange={(e) => setLastName(e.target.value)} />
                        {displayErrors && lastName.trim() === "" && <div className="field-message">Please enter your Last Name</div>}
                    </div>
                    <div className="input">
                        <i className='far fa-calendar-alt'></i>
                        <input type="date" value={dateOfBirth} onChange={(e) => setDateOfBirth(e.target.value)} />
                    </div>
                    <div className="input">
                        <label>Gender:</label>
                        <select value={gender} onChange={(e) => setGender(e.target.value)}>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                        </select>
                    </div>
                    <div className="input">
                        <label>User Type:</label>
                        <select value={userType} onChange={(e) => setUserType(e.target.value)}>
                            <option value="">Select User Type</option>
                            <option value="student">Student</option>
                            <option value="instructor">Instructor</option>
                        </select>
                        {displayErrors && userType === "" && <div className="field-message">Please select a user type</div>}
                    </div>
                    <div className="input">
                        <i className='fa fa-phone'></i>
                        <input type="tel" placeholder="Phone Number" value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} />
                        {displayErrors && phoneError !== "" && <div className="field-message">Please enter your phone number</div>}
                    </div>
                    <div className="input">
                        <img src={email_icon} alt="" />
                        {action === "Sign Up" && (
                            <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
                        )}
                        {action === "Login" && (
                            <input type="email" placeholder="Email" value="" onChange={(e) => setEmail("")} />
                        )}
                        {displayErrors && email.trim() === "" && <div className="field-message">Invalid email format (e.g., user@example.com)</div>}
                    </div>
                    <div className="input">
                        <img src={password_icon} alt="" />
                        {action === "Sign Up" && (
                            <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
                        )}
                        {action === "Login" && (
                            <input type="password" placeholder="Password" value="" onChange={(e) => setPassword("")} />
                        )}
                        {displayErrors && password.trim() === "" && <div className="field-message">Password must be at least 8 characters</div>}
                    </div>
                </form>

                {action === "Sign Up" && (
                    <div>
                        <button className="submit-button" onClick={handleSignup}>Submit</button>
                    </div>
                )}

                <div className="submit-container">
                    <div className="submit-login" onClick={handleLogin}>Login</div>
                </div>
            </div>
        </>
    );
};

export default SIGNUP;
