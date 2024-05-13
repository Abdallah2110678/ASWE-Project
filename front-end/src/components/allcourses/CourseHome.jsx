import React, { useEffect, useState } from "react"
import Back from "../common/back/Back"
import CoursesCard from "./CoursesCard"
import OnlineCourses from "./OnlineCourses"
import { REST_API_BASE_URL } from "./../../App";
import axios from "axios";
import $ from 'jquery';
const CourseHome = () => {


  const [courses, setCourses] = useState([]);

    useEffect(() => {
        fetchCourses();
        console.log(courses);
    }, []);
    

    const fetchCourses = async () => {
        try {
            const response = await axios.get(`${REST_API_BASE_URL}/instructor/allcourse`);
            setCourses(response.data);
            console.log(courses);
        } catch (error) {
            console.error('Error fetching courses:', error);
        }
    };
    const [searchQuery, setSearchQuery] = useState('');
   
  
    const handleSearch = () => {
      if (searchQuery.trim() !== '') {
        axios.get(`${REST_API_BASE_URL}/student/search?title=${searchQuery}`)
          .then(response => {
            setCourses(response.data);
          })
          .catch(error => {
            console.error('Error fetching courses:', error);
          });
      }
    };
  
    const handleChange = event => {
      setSearchQuery(event.target.value);
    };
  
    const handleKeyPress = event => {
      if (event.key === 'Enter') {
        handleSearch();
      }
    };
  return (
    <>
      <Back title='Explore Courses' />
      <div className="container d-flex justify-content-center mt-5">
      <div className="row">
        <div className="col-md-8">
          <input
            type="text"
            value={searchQuery}
            onChange={handleChange}
            onKeyPress={handleKeyPress}
            className="form-control"
            placeholder="Search by title..."
          />
        </div>
        <div className="col-md-4">
          <button onClick={handleSearch} className="btn btn-primary mx-2">Search</button>
          <button onClick={fetchCourses} className="btn btn-secondary">All Course</button>
        </div>
      </div>
    </div>
      <CoursesCard courses={courses}/>
      {/* <OnlineCourses /> */}
    </>
  )
}

export default CourseHome

