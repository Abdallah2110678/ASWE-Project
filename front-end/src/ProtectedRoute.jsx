import { Navigate, Route, Routes } from "react-router-dom";
import { Store } from "./store";
import { useContext } from "react";

function ProtectedRoute({ element, allowedRoles }) {
  const { state } = useContext(Store);
  const { userInfo } = state;

  // If user is not logged in, redirect to login page
  if (!userInfo) {
    return <Navigate to="/login" />;
  }

  // If user's role is not allowed, redirect to home page
  if (!allowedRoles.includes(userInfo.role)) {
    return <Navigate to="/" />;
  }

  // Otherwise, render the element
  return element;
}


export default ProtectedRoute;