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
      <div className={'navigation ${active ? "active" : ""}'}>
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
            <a href="/">
              <span className="iconn"></span>
              <span className="title">Home</span>
            </a>
          </li>
          <li onClick={handleMouseOver}>
            <a href="/student/dashboard">
              <span className="iconn"></span>
              <span className="title">Dashboard</span>
            </a>
          </li>

          <li onClick={handleMouseOver}>
            <a href="#">
              <span className="iconn">
                <ion-icon name="people-outline"></ion-icon>
              </span>
              <span className="title">Courses</span>
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
        </ul>
      </div>
    </div>
  );
};

export default Aside_Instructor;