import React, { useEffect, useState } from "react"
import Back from "../common/back/Back"
import CoursesCard from "./CoursesCard"
import OnlineCourses from "./OnlineCourses"
import { REST_API_BASE_URL } from "./../../App";
import axios from "axios";
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
  
  return (
    <>
      <Back title='Explore Courses' />
      <CoursesCard courses={courses}/>
      <OnlineCourses />
    </>
  )
}

export default CourseHome
