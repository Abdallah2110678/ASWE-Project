import "./style.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPencilAlt, faTrash } from "@fortawesome/free-solid-svg-icons";
import { useState } from "react";
import { Link } from "react-router-dom";

export function Students() {
  const [active, setActive] = useState(false);

  const toggleMenu = () => {
    setActive(!active);
  };
  const crudData = [
    {
      id: 1,
      first_name: "John",
      last_name: "Doe",
      email: "john@example.com",
      gender: "Male",
    },
    {
      id: 2,
      first_name: "Jane",
      last_name: "Doe",
      email: "jane@example.com",
      gender: "Female",
    },
    // Add more data as needed
  ];

  return (
    <div className={`main ${active ? "active" : ""}`}>
      <div className="topbar">
        <div className="toggle" onClick={toggleMenu}>
          <ion-icon name="menu-outline"></ion-icon>
        </div>
        <div className="search">
          <label>
            <input type="text" placeholder="Search here" />
            <ion-icon name="search-outline"></ion-icon>
          </label>
        </div>

        <div className="user">
          <img src="assets/imgs/customer01.jpg" alt="" />
        </div>
      </div>
      <div style={{padding:"5px"}}>
      {/* Your message alert */}
      <Link to="/admin/student-add" className="btn btn-dark mb-3">
        Add New
      </Link>

      <table className="table table-hover text-center">
        <thead className="table-dark">
          <tr>
            <th scope="col">ID</th>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Email</th>
            <th scope="col">Gender</th>
            <th scope="col">Action</th>
          </tr>
        </thead>
        <tbody>
          {crudData.map((item) => (
            <tr key={item.id}>
              <td>{item.id}</td>
              <td>{item.first_name}</td>
              <td>{item.last_name}</td>
              <td>{item.email}</td>
              <td>{item.gender}</td>
              <td>
                <Link to={`/admin/student-edit`} className="link-dark">
                  <FontAwesomeIcon icon={faPencilAlt} className="me-3" />
                </Link>
                <Link to={`/admin/student-delete`} className="link-dark">
                  <FontAwesomeIcon icon={faTrash} />
                </Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      </div>
    </div>
  );
}







