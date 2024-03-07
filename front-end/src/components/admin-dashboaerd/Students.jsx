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





 export function EditStudent() {
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        password: '',
        email: '',
        gender: '',
        phone: '',
        dob: ''
    });
    const [active, setActive] = useState(false);

    const toggleMenu = () => {
        setActive(!active);
      };
    // Fetch user data (similar to backend) - You can replace this with your own logic

    // Function to handle form field changes
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    // Function to handle form submission
    const handleSubmit = (e) => {
        e.preventDefault();
        // Here you can add code to submit the form data
        console.log(formData);
    };

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
      <div className="container">
            <h3>Edit Student</h3>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="firstName" className="form-label">First Name:</label>
                    <input type="text" className="form-control" id="firstName" name="firstName" value={formData.firstName} onChange={handleInputChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="lastName" className="form-label">Last Name:</label>
                    <input type="text" className="form-control" id="lastName" name="lastName" value={formData.lastName} onChange={handleInputChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="password" className="form-label">Password:</label>
                    <input type="password" className="form-control" id="password" name="password" value={formData.password} onChange={handleInputChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="email" className="form-label">Email:</label>
                    <input type="email" className="form-control" id="email" name="email" value={formData.email} onChange={handleInputChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="gender" className="form-label">Gender:</label>
                    <select className="form-select" id="gender" name="gender" value={formData.gender} onChange={handleInputChange}>
                        <option value="">Select Gender</option>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="phone" className="form-label">Phone:</label>
                    <input type="tel" className="form-control" id="phone" name="phone" value={formData.phone} onChange={handleInputChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="dob" className="form-label">Date of Birth:</label>
                    <input type="date" className="form-control" id="dob" name="dob" value={formData.dob} onChange={handleInputChange} />
                </div>
                <button type="submit" className="btn btn-primary">Save Changes</button>
            </form>
        </div>
        </div>
    );
}




export function AddStudent() {
    const [active, setActive] = useState(false);

  const toggleMenu = () => {
    setActive(!active);
  };
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        password: '',
        email: '',
        gender: '',
        phone: '',
        dob: ''
    });

    // Function to handle form field changes
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    // Function to handle form submission
    const handleSubmit = (e) => {
        e.preventDefault();
        // Here you can add code to submit the form data
        console.log(formData);
    };

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
            <div className="container">
                <h3>Add New Student</h3>
                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label htmlFor="firstName" className="form-label">First Name:</label>
                        <input type="text" className="form-control" id="firstName" name="firstName" value={formData.firstName} onChange={handleInputChange} />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="lastName" className="form-label">Last Name:</label>
                        <input type="text" className="form-control" id="lastName" name="lastName" value={formData.lastName} onChange={handleInputChange} />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="password" className="form-label">Password:</label>
                        <input type="password" className="form-control" id="password" name="password" value={formData.password} onChange={handleInputChange} />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="email" className="form-label">Email:</label>
                        <input type="email" className="form-control" id="email" name="email" value={formData.email} onChange={handleInputChange} />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="gender" className="form-label">Gender:</label>
                        <select className="form-select" id="gender" name="gender" value={formData.gender} onChange={handleInputChange}>
                            <option value="">Select Gender</option>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                        </select>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="phone" className="form-label">Phone:</label>
                        <input type="tel" className="form-control" id="phone" name="phone" value={formData.phone} onChange={handleInputChange} />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="dob" className="form-label">Date of Birth:</label>
                        <input type="date" className="form-control" id="dob" name="dob" value={formData.dob} onChange={handleInputChange} />
                    </div>
                    <button type="submit" className="btn btn-primary">Add Student</button>
                </form>
            </div>
        </div>
    );
}


