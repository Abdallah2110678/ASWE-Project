import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import Head from "./Head";
import "./header.css";
import axios from "axios";
import { REST_API_BASE_URL } from "./../../../App";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faShoppingCart, faTrash } from "@fortawesome/free-solid-svg-icons";

const Header = () => {
  const [click, setClick] = useState(false);
  const [str, setStr] = useState("");

  useEffect(() => {
    const authToken = localStorage.getItem("authToken");
    if (authToken) {
      axios
        .get(`${REST_API_BASE_URL}/user`, {
          headers: {
            Authorization: `Bearer ${authToken}`,
          },
        })
        .then((response) => {
          setStr(response.data.value);
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    }
  }, []);

  return (
    <>
      <Head />
      <header>
        <nav className="flexSB">
          <ul
            className={click ? "mobile-nav" : "flexSB "}
            onClick={() => setClick(false)}
          >
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/courses">All Courses</Link>
            </li>
            <li>
              <Link to="/about">About</Link>
            </li>
            <li>
              <Link to="/team">Team</Link>
            </li>
            <li>
              <Link to="/pricing">Pricing</Link>
            </li>
            <li>
              <Link to="/journal">Journal</Link>
            </li>
            <li>
              <Link to="/contact">Contact</Link>
            </li>
            <li>
              <Link to="/contact">{str}</Link>
            </li>
            <li>
              <Link to="/login">Login</Link>
            </li>
            <li>
              <Link to="/userProfile">User Profile</Link>
            </li>
            <CartItems />
          </ul>
          <div className="start">
            <div className="button">GET CERTIFICATE</div>
          </div>
          <button className="togglee" onClick={() => setClick(!click)}>
            {click ? (
              <i className="fa fa-times"> </i>
            ) : (
              <i className="fa fa-bars"></i>
            )}
          </button>
        </nav>
      </header>
    </>
  );
};

function CartItems() {
  const [cartItems, setCartItems] = useState([]);

  useEffect(() => {
    const fetchCartItems = async () => {
      try {
        const response = await axios.get(`${REST_API_BASE_URL}/student/cart/2`);
        setCartItems(response.data);
      } catch (error) {
        console.error("Error fetching cart items:", error);
      }
    };

    fetchCartItems();
  }, []);
  const handleDelete = async (cartItemId) => {
    try {
      const response = await axios.delete(
        `${REST_API_BASE_URL}/student/cart/delete/${cartItemId}`
      );
      if (response.status === 200) {

        console.log(`Cart item with ID ${cartItemId} deleted successfully.`);
      }
    } catch (error) {
      console.error("Error deleting cart item:", error.message);
    }
  };

  // Function to calculate the total sum of prices
  const calculateTotalPrice = () => {
    let totalPrice = 0;
    cartItems.forEach((cartItem) => {
      totalPrice += cartItem.course.price;
    });
    return totalPrice;
  };

  return (
    <>
      <div className="dropdown">
        <button
          className="btn  dropdown-toggle"
          type="button"
          id="dropdownMenuButton1"
          data-bs-toggle="dropdown"
          aria-expanded="false"
        >
          <FontAwesomeIcon icon={faShoppingCart} size="lg"/>
          <span className="badge ">{cartItems.length}</span>
        </button>
        <ul className="dropdown-menu" aria-labelledby="dropdownMenuButton1"  style={{ maxHeight: "400px", overflowY: "auto" }}>
          {cartItems.map((cartItem, index) => (
            <li
              key={cartItem.id}
              className="dropdown-item d-flex justify-content-between align-items-center"
            >
              <div style={{ fontFamily: `${cartItem.course.fontFamily}` }}>
                <span class="fw-bold">{cartItem.course.title}</span>
                <br />
                Price: ${cartItem.course.price}
                <br />
                By: {""}
              </div>
              <button
                className="btn btn-danger"
                onClick={() => handleDelete(cartItem.id)}
              >
                <FontAwesomeIcon icon={faTrash} />
              </button>
            </li>
          ))}
          {cartItems.length !== 0 ? (
            <>
              <li>
                <hr className="dropdown-divider" />
              </li>
              <li> Total Price: ${calculateTotalPrice()}</li>
              <Link to="#">
                <button className=" btn btn-primary"> Go to The Cart </button>
              </Link>
            </>
          ) : (
            <li> Not Found Item in Cart</li>
          )}
        </ul>
      </div>
    </>
  );
}

export default Header;
