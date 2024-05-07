import { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import axios from "axios";
    import {REST_API_BASE_URL} from "./../../App";

const CreateCourse =() =>{
    const [courseData, setCourseData] = useState({
        title: "",
        description: "",
        tags: "",
        price: "",
        categoryId: "", // Assuming categoryId is the ID of the category for the course
      });
    
      const [categories, setCategories] = useState([]);
      const [errors, setErrors] = useState({});
      const [successMessage, setSuccessMessage] = useState("");
    
      useEffect(() => {
        fetchCategories();
      }, []);

      useEffect(() => {
        const timer = setTimeout(() => {
          setSuccessMessage("");
        }, 4000);
        return () => clearTimeout(timer);
      }, [successMessage]);
    
      const fetchCategories = async () => {
        try {
          const response = await axios.get(`${REST_API_BASE_URL}/instructor/categories`);
          setCategories(response.data);
        } catch (error) {
          console.error("Error fetching categories:", error);
          // Handle specific errors or display a generic error message
        }
      };
    
      const handleChange = (e) => {
        const { name, value } = e.target;
        setCourseData({ ...courseData, [name]: value });
        setErrors({ ...errors, [name]: validateField(name, value) });
      };
    
      const validateField = (name, value) => {
        switch (name) {
          case "title":
            return !value.trim() ? "Title is required." : "";
          case "description":
            return !value.trim() ? "Description is required." : "";
          case "tags":
            return !value.trim() ? "Tags are required." : "";
          case "price":
            return isNaN(value) ? "Price must be a number." : "";
          case "categoryId":
            return !value ? "Category is required." : "";
          default:
            return "";
        }
      };
    
      const handleSubmit = async (e) => {
        e.preventDefault();
        const formErrors = {};
        for (const key in courseData) {
          if (courseData.hasOwnProperty(key)) {
            formErrors[key] = validateField(key, courseData[key]);
          }
        }
        if (Object.values(formErrors).some((error) => error)) {
          setErrors(formErrors);
          return;
        }
        try {
          const response = await axios.post(`${REST_API_BASE_URL}/instructor/createcourse?categoryId=${courseData.categoryId}`, courseData);
          console.log("Course created:", response.data);
          setSuccessMessage("Course added successfully.");
          setCourseData({
            title: "",
            description: "",
            tags: "",
            price: "",
            categoryId: "",
          });
        } catch (error) {
          console.error("Error creating course:", error);
          // Handle specific errors or display a generic error message
        }
      };
return (
    <div className="main">
      <div className="detailss">
        <div className="recentOrderss">
          <div className="cardHeader">
            <h2>Create New Course</h2>
            <Link to="/instructor/my-courses/" className="btn">
              All Courses
            </Link>
          </div>
          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label htmlFor="title" className="form-label">
                Title:
              </label>
              <input
                type="text"
                className="form-control"
                id="title"
                name="title"
                value={courseData.title}
                onChange={handleChange}
              />
              {errors.title && <div className="error text-danger">{errors.title}</div>}
            </div>
            <div className="mb-3">
              <label htmlFor="description" className="form-label">
                Description:
              </label>
              <textarea
                className="form-control"
                id="description"
                name="description"
                value={courseData.description}
                onChange={handleChange}
              />
              {errors.description && <div className="error text-danger">{errors.description}</div>}
            </div>
            <div className="mb-3">
              <label htmlFor="tags" className="form-label">
                Tags:
              </label>
              <input
                type="text"
                className="form-control"
                id="tags"
                name="tags"
                value={courseData.tags}
                onChange={handleChange}
              />
              {errors.tags && <div className="error text-danger">{errors.tags}</div>}
            </div>
            <div className="mb-3">
              <label htmlFor="price" className="form-label">
                Price:
              </label>
              <input
                type="text"
                className="form-control"
                id="price"
                name="price"
                value={courseData.price}
                onChange={handleChange}
              />
              {errors.price && <div className="error text-danger">{errors.price}</div>}
            </div>
            <div className="mb-3">
              <label htmlFor="categoryId" className="form-label">
                Category:
              </label>
              <select
                className="form-select"
                id="categoryId"
                name="categoryId"
                value={courseData.categoryId}
                onChange={handleChange}
              >
                <option value="">Select Category</option>
                {categories.map((category) => (
                  <option key={category.id} value={category.id}>
                    {category.name}
                  </option>
                ))}
              </select>
              {errors.categoryId && <div className="error text-danger">{errors.categoryId}</div>}
            </div>
            <button type="submit" className="btn btn-primary">
              Add Course
            </button>
            {successMessage && <div className="alert alert-success mt-3">{successMessage}</div>}
          </form>
        </div>
      </div>
    </div>
  );
};
     

export default CreateCourse;