import React, { useState } from 'react';



const Aside_Admin = () => {
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
            <a href="/admin/dashboard">
              <span className="iconn">
                
              </span>
              <span className="title">Dashboard</span>
            </a>
          </li>
          <li onClick={handleMouseOver}>
            <a href="/admin/students">
              <span className="iconn">
                <ion-icon name="home-outline"></ion-icon>
              </span>
              <span className="title">Students</span>
            </a>
          </li>
          <li onClick={handleMouseOver}>
            <a href="/admin/student-add">
              <span className="iconn">
                <ion-icon name="home-outline"></ion-icon>
              </span>
              <span className="title">Add Student</span>
            </a>
          </li>
          <li onClick={handleMouseOver}>
            <a href="/admin/instructors">
              <span className="iconn">
                <ion-icon name="home-outline"></ion-icon>
              </span>
              <span className="title">Instructors</span>
            </a>
          </li>
          {/* Add more list items here */}
        </ul>
      </div>
    </div>
  );
};

export default Aside_Admin;
