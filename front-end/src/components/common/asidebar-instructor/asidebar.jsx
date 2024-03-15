import React, { useState } from "react";

const Aside_Instructor = () => {
  const [active, setActive] = useState(false);

  const toggleMenu = () => {
    setActive(!active);
  };

  const handleMouseOver = (e) => {
    const listItems = document.querySelectorAll(".navigation li");
    listItems.forEach((item) => {
      item.classList.remove("hovered");
    });
    e.target.classList.add("hovered");
  };

  return (
    <div>
      {/* Navigation */}
      <div className={`navigation ${active ? "active" : ""}`}>
        <ul>
          <li onClick={handleMouseOver}>
            <a href="#">
              <span className="iconn">
                <ion-icon name="logo-apple"></ion-icon>
              </span>
              <span className="title">e-learning</span>
            </a>
          </li>
          <li onClick={handleMouseOver}>
            <a href="/instructor/dashboard">
              <span className="iconn"></span>
              <span className="title">Dashboard</span>
            </a>
          </li>
          <li onClick={handleMouseOver}>
            <a href="#">
              <span className="iconn">
                <ion-icon name="document-text-outline"></ion-icon>
              </span>
              <span className="title">Courses</span>
            </a>
          </li>
          <li onClick={handleMouseOver}>
            <a href="#">
              <span className="iconn">
                <ion-icon name="people-outline"></ion-icon>
              </span>
              <span className="title">Students</span>
            </a>
          </li>
          <li onClick={handleMouseOver}>
            <a href="#">
              <span className="iconn">
                <ion-icon name="log-out-outline"></ion-icon>
              </span>
              <span className="title">Log Out</span>
            </a>
          </li>
          {/* Add more list items here */}
        </ul>
      </div>
    </div>
  );
};

export default Aside_Instructor;
