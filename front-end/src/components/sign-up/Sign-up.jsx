import React, { useState } from "react";
import Back from "../common/back/Back";
import './Sign-up.css';

import user_icon from '../../assets/person.png';
import email_icon from '../../assets/email.png';
import password_icon from '../../assets/password.png';

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

    const handleSignup = () => {
        // Email validation
        if (!email.includes("@") && !email.includes(".com")) {
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

        // If email, password, and phone number are valid, proceed with signup
        console.log("First Name:", firstName);
        console.log("Last Name:", lastName);
        console.log("Email:", email);
        console.log("Password:", password);
        console.log("Phone Number:", phoneNumber);
        console.log("Date of Birth:", dateOfBirth);
        console.log("Gender:", gender);
    };

    const canSwitchToLogin = () => {
        if (action === "Sign Up") {
            // Check if all required fields are filled and error-free
            return (
                firstName.trim() !== "" &&
                lastName.trim() !== "" &&
                email.trim() !== "" &&
                password.trim() !== "" &&
                emailError === "" &&
                passwordError === "" &&
                phoneError === ""
            );
        }
        return email.trim() !== "" && password.trim() !== "" && emailError === "" && passwordError === "" && phoneError === "";
    };

    const handleLogin = () => {
        setDisplayErrors(true);

        if (!canSwitchToLogin()) {
            // Display an error message or take appropriate action
            console.error("Please complete the required fields in the sign-up form");
            return;
        }

        setAction("Login");
        handleSignup();
    };

    return (
        <>
            <Back title='SIGN UP' />
            <div className="Container">
                <div className="Header">
                    <div className="Text">{action}</div>
                    <div className="underline"></div>
                </div>
                <div className="inputs">
                    {action === "Login" ? (
                        <div></div>
                    ) : (
                        <>
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
                                <i className='fa fa-phone'></i>
                                <input type="tel" placeholder="Phone Number" value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} />
                                {displayErrors && phoneError !== "" && <div className="field-message">{phoneError}</div>}
                            </div>
                        </>
                    )}

                    <div className="input">
                        <img src={email_icon} alt="" />
                        <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
                        {displayErrors && emailError !== "" && <div className="field-message">{emailError}</div>}
                    </div>
                    <div className="input">
                        <img src={password_icon} alt="" />
                        <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
                        {displayErrors && passwordError !== "" && <div className="field-message">{passwordError}</div>}
                    </div>
                </div>
                {action === "Sign Up" ? <div></div> : <div className="forgot-password">Forgot password? <span>Click Here!</span></div>}
                <div className="submit-container">
                    <div className={action === "Login" ? "submit gray" : "submit"} onClick={() => { setAction("Sign Up") }}>Sign Up</div>
                    <div className={action === "Sign Up" ? "submit gray" : "submit"} onClick={handleLogin}>Login</div>
                </div>
            </div>
        </>
    );
};

export default SIGNUP;
