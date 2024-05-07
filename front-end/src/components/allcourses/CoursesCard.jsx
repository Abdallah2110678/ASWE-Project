import React from "react"
import "./courses.css"
import { coursesCard } from "../../dummydata"

const CoursesCard = ({courses}) => {

  return (
    <>
      <section className='coursesCard'>
        <div className='container grid2'>
          {courses.map((course) => (
            <div className='items'>
              <div className='content flex'>
                <div className='left'>
                  <div className='img'>
                    {/* picture of course */}
                    <img src={""} alt='' />
                  </div>
                </div>
                <div className='text'>
                  <h1>{course.title}</h1>
                  <div className='rate'>
                    <i className='fa fa-star'></i>
                    <i className='fa fa-star'></i>
                    <i className='fa fa-star'></i>
                    <i className='fa fa-star'></i>
                    <i className='fa fa-star'></i>
                    <label htmlFor=''>(5.0)</label>
                  </div>
                  <div className='details'>
                    
                      <> {course.user ? (
                      <div className='box'>
                        <div className='dimg'>
                          {/* display user image here */}
                          <img src={""} alt='' />
                        </div>
                        <div className='para'>
                          <h4>By {course.user.fname}</h4>
                        </div>
                      </div>
                    ):(<div className='box'>
                    <div className='dimg'>
                      {/* display user image here */}
                      <img src={""} alt='' />
                    </div>
                    <div className='para'>
                      <h4>By Admin</h4>
                    </div>
                  </div>)}
                    {course.courseMaterials.length ?(
                      <span>({course.courseMaterials.length}) Video</span>
                    ):(
                        <span>({course.courseMaterials.length}) Videos Not Found</span>
                        )}
                      
                      </>
                    
                  </div>
                </div>
              </div>
              <div className='price'>
              {course.price ?(
                <h3>
                 $ {course.price} All Course
                </h3>):(
                  <h3>
                   Free Course
                  </h3>
                )}
              </div>
              <button className='outline-btn'>ENROLL NOW !</button>
            </div>
          ))}
        </div>
      </section>
    </>
  )
}

export default CoursesCard
