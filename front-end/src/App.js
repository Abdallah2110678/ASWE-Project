import "./App.css";
import Header from "./components/common/header/Header";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Outlet,
} from "react-router-dom";
import About from "./components/about/About";
import CourseHome from "./components/allcourses/CourseHome";
import Team from "./components/team/Team";
import Pricing from "./components/pricing/Pricing";
import Blog from "./components/blog/Blog";
import Contact from "./components/contact/Contact";
import Footer from "./components/common/footer/Footer";
import Home from "./components/home/Home";
import SIGNUP from "./components/sign-up/Sign-up";
import LOGIN from "./components/Log-in/Log-in";
import Aside_Instructor from "./components/common/asidebar-instructor/asidebar";
import DASHBOARD from "./components/instructor-dashboard/Dashboard";

import Aside_Admin from "./components/common/asidebar-admin/asidebar";
import Dashboard from "./components/admin-dashboaerd/Dashboard";
import EditFormStudent from "./components/admin-dashboaerd/Students/EditFormStudent";
import AllStudents from "./components/admin-dashboaerd/Students/AllStudent";
import CreateNewStudent from "./components/admin-dashboaerd/Students/CreateNewStudent";

import All_Instructor from "./components/admin-dashboaerd/Instructor/All_Instructor";
import CreateNewInstructor from "./components/admin-dashboaerd/Instructor/CreateNewInstructor";
import EditFormInstructor from "./components/admin-dashboaerd/Instructor/EditFormInstructor";
export const REST_API_BASE_URL = "http://localhost:9090/api";
function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route
            path="/admin"
            element={
              <>
                <Aside_Admin />
                <Outlet />
              </>
            }
          >
            <Route path="dashboard" element={<Dashboard />} />
            <Route path="students" element={<AllStudents />} />
            <Route path="student-add" element={<CreateNewStudent />} />
            <Route path="student-edit/:id" element={<EditFormStudent />} />
            <Route path="instructors" element={<All_Instructor />} />
            <Route path="instructor-add" element={<CreateNewInstructor />} />
            <Route
              path="instructor-edit/:id"
              element={<EditFormInstructor />}
            />
          </Route>

          <Route
            path="/instructor"
            element={
              <>
                <Aside_Instructor />
                <Outlet />
              </>
            }
          >
            <Route path="dashboard" element={<DASHBOARD />} />
          </Route>

          <Route
            path="/"
            element={
              <>
                <Header />
                <Outlet />
                <Footer />
              </>
            }
          >
            <Route path="/" element={<Home />} />
            <Route path="/about" element={<About />} />
            <Route path="/courses" element={<CourseHome />} />
            <Route path="/team" element={<Team />} />
            <Route path="/pricing" element={<Pricing />} />
            <Route path="/journal" element={<Blog />} />
            <Route path="/contact" element={<Contact />} />
            <Route exact path="/sign-up" element={<SIGNUP />} />
            <Route exact path="/login" element={<LOGIN />} />
          </Route>
        </Routes>
      </Router>
    </>
  );
}

export default App;
