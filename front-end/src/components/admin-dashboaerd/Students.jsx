import "./style.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPencilAlt, faTrash } from "@fortawesome/free-solid-svg-icons";
import { useState ,useEffect} from "react";
import { Link, useParams } from "react-router-dom";
import axios from "axios";

export function Students() {
  const [active, setActive] = useState(false);
  const [Students ,setStudents] =useState([]);


  const handleDelete = async (id) => {
    try {
      // Make a DELETE request to your server using Axios
      const response = await axios.delete(`/student/delete/${id}`);

      // Check if the request was successful
      if (response.status === 200) {
        // Handle success, e.g., update state or display a success message
        console.log("Item deleted successfully");
        // Update the user list after successful deletion
        setStudents(Students.filter((Student) => Student.id !== id));
      } else {
        // Handle other statuses, e.g., display an error message
        console.error("Failed to delete item");
      }
    } catch (error) {
      // Handle errors, e.g., display an error message
      console.error("Error deleting item:", error.message);
    }
  };


  useEffect(() => {
    fetch('/student')
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then((data) => {
        console.log(data); // For debugging
        setStudents(data);
      })
      .catch((error) => {
        console.error('Error fetching students:', error);
      });
  },[]);

  const toggleMenu = () => {
    setActive(!active);
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
          {Students.map((item) => (
            <tr key={item.id}>
              <td>{item.id}</td>
              <td>{item.fname}</td>
              <td>{item.lname}</td>
              <td>{item.email}</td>
              <td>{item.gender}</td>
              <td>
                <Link to={`/admin/student-edit/${item.id}`} className="link-dark">
                  <FontAwesomeIcon  icon={faPencilAlt} className="me-3" />
                </Link>


                <button
                  className="btn "
                  onClick={() => handleDelete(item.id)}
                >
                  <FontAwesomeIcon icon={faTrash} />
                </button>
                  
                 
                
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
  const { id } = useParams();
  console.log(id);
  const [studentData, setStudentData] = useState({
    fname: '',
    lname: '',
    password: '',
    gender: '',
    dob: '',
    email: ''
  });
  const [active, setActive] = useState(false);

  const toggleMenu = () => {
    setActive(!active);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setStudentData({ ...studentData, [name]: value });
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        console.log(id);
        const response = await axios.get(`/student/${id}`);
        setStudentData(response.data);
      } catch (error) {
        console.error("Error fetching student data:", error);
      }
    };
    fetchData();
  },[])

  const handleSubmit = (e) => {
    e.preventDefault();
    // Make PUT request to update student data
    axios
      .put(`/student/update/${id}`, studentData)
      .then((response) => {
        console.log("Student updated successfully:", response.data);
        
      })
      .catch((error) => {
        console.error("Error updating student:", error);
      });
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
            <input type="text" className="form-control" id="firstName" name="fname" value={studentData.fname} onChange={handleChange} />
          </div>
          <div className="mb-3">
            <label htmlFor="lastName" className="form-label">Last Name:</label>
            <input type="text" className="form-control" id="lastName" name="lname" value={studentData.lname} onChange={handleChange} />
          </div>
          <div className="mb-3">
            <label htmlFor="password" className="form-label">Password:</label>
            <input type="password" className="form-control" id="password" name="password" value={studentData.password} onChange={handleChange} />
          </div>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">Email:</label>
            <input type="email" className="form-control" id="email" name="email" value={studentData.email} onChange={handleChange} />
          </div>
          <div className="mb-3">
            <label htmlFor="gender" className="form-label">Gender:</label>
            <select className="form-select" id="gender" name="gender" value={studentData.gender} onChange={handleChange}>
              <option value="">Select Gender</option>
              <option value="male">Male</option>
              <option value="female">Female</option>
            </select>
          </div>
          <div className="mb-3">
            <label htmlFor="dob" className="form-label">Date of Birth:</label>
            <input type="date" className="form-control" id="dob" name="dob" value={studentData.dob} onChange={handleChange} />
          </div>
          <button type="submit" className="btn btn-primary">Update Student</button>
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
  const [studentData, setStudentData] = useState({
    fname: '',
    lname: '',
    password: '',
    gender: '',
    dob: '',
    email: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setStudentData({ ...studentData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    axios.post('/student/create', studentData)
      .then((response) => {
        console.log('Student created:', response.data);
        // Optionally, handle success here (e.g., show a success message)
      })
      .catch((error) => {
        console.error('Error creating student:', error);
        // Optionally, handle error here (e.g., show an error message)
      });
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
        <input type="text" className="form-control" id="firstName" name="fname" value={studentData.fname} onChange={handleChange} />
    </div>
    <div className="mb-3">
        <label htmlFor="lastName" className="form-label">Last Name:</label>
        <input type="text" className="form-control" id="lastName" name="lname" value={studentData.lname} onChange={handleChange} />
    </div>
    <div className="mb-3">
        <label htmlFor="password" className="form-label">Password:</label>
        <input type="password" className="form-control" id="password" name="password" value={studentData.password} onChange={handleChange} />
    </div>
    <div className="mb-3">
        <label htmlFor="email" className="form-label">Email:</label>
        <input type="email" className="form-control" id="email" name="email" value={studentData.email} onChange={handleChange} />
    </div>
    <div className="mb-3">
        <label htmlFor="gender" className="form-label">Gender:</label>
        <select className="form-select" id="gender" name="gender" value={studentData.gender} onChange={handleChange}>
            <option value="">Select Gender</option>
            <option value="male">Male</option>
            <option value="female">Female</option>
        </select>
    </div>
    <div className="mb-3">
        <label htmlFor="dob" className="form-label">Date of Birth:</label>
        <input type="date" className="form-control" id="dob" name="dob" value={studentData.dob} onChange={handleChange} />
    </div>
    <button type="submit" className="btn btn-primary">Add Student</button>
</form>
            </div>
        </div>
    );
}


