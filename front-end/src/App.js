import './App.css';
import Header from "./components/common/header/Header"
import { BrowserRouter as Router ,Routes, Route, Outlet } from "react-router-dom"
import About from "./components/about/About"
import CourseHome from "./components/allcourses/CourseHome"
import Team from "./components/team/Team"
import Pricing from "./components/pricing/Pricing"
import Blog from "./components/blog/Blog"
import Contact from "./components/contact/Contact"
import Footer from "./components/common/footer/Footer"
import Home from "./components/home/Home"
import SIGNUP, { LOGIN } from "./components/sign-up/Sign-up"

import Aside_Admin from './components/common/asidebar-admin/asidebar';
import Dashboard from "./components/admin-dashboaerd/Dashboard";
import { AddStudent, EditStudent, Students } from './components/admin-dashboaerd/Students';

function App() {

  return (
    <>
    <Router>
      <Routes>
          <Route path="/admin" element={<><Aside_Admin/>
            <Outlet />
          </>}>
            <Route path="dashboard" element={ < Dashboard /> } />
            <Route path="students" element={<Students/>} />
            <Route path="student-add" element={<AddStudent/>} />
            <Route path="student-edit" element={<EditStudent/>} />
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
            <Route exact path='/sign-up' element={<SIGNUP />}/>
            <Route exact path='/LOGIN' element={<LOGIN />}/>
          </Route>
      </Routes>
    </Router>
  </>
  );
}

export default App;
