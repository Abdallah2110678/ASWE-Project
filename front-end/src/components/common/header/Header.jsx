import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import Head from "./Head";
import "./header.css";
import axios from "axios";
import { REST_API_BASE_URL } from "./../../../App";

const Header = () => {
  const [click, setClick] = useState(false);
  const [str, setStr] = useState("");

  useEffect(() => {
    const authToken = localStorage.getItem("authToken");
    if (authToken) {
      axios.get(`${REST_API_BASE_URL}/user`, {
        headers: {
          Authorization: `Bearer ${authToken}`
        }
      })
        .then(response => {
          setStr(response.data.value);
        })
        .catch(error => {
          console.error('Error fetching data:', error);
        });
    }
  }, []);

  return (
    <>
      <Head />
      <header>
        <nav className='flexSB'>
          <ul className={click ? "mobile-nav" : "flexSB "} onClick={() => setClick(false)}>
            <li>
              <Link to='/'>Home</Link>
            </li>
            <li>
              <Link to='/courses'>All Courses</Link>
            </li>
            <li>
              <Link to='/about'>About</Link>
            </li>
            <li>
              <Link to='/team'>Team</Link>
            </li>
            <li>
              <Link to='/pricing'>Pricing</Link>
            </li>
            <li>
              <Link to='/journal'>Journal</Link>
            </li>
            <li>
              <Link to='/contact'>Contact</Link>
            </li>
            <li>
              <Link to='/contact'>{str}</Link>
            </li>
            <li>
              <Link to='/login'>Login</Link>
            </li>
            <li>
              <Link to='/userProfile'>User Profile</Link>
            </li>
          </ul>
          <div className='start'>
            <div className='button'>GET CERTIFICATE</div>
          </div>
          <button className='toggle' onClick={() => setClick(!click)}>
            {click ? <i className='fa fa-times'> </i> : <i className='fa fa-bars'></i>}
          </button>
        </nav>
      </header>
    </>
  );
};

export default Header;
