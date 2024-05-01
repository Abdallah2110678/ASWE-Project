// package com.example.aswe.demo.controllers;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.server.ResponseStatusException;
// import org.springframework.web.servlet.ModelAndView;

// import com.example.aswe.demo.models.Category;
// import com.example.aswe.demo.models.Course;
// import com.example.aswe.demo.models.CourseMaterial;
// import com.example.aswe.demo.repository.CategoryRepository;
// import com.example.aswe.demo.repository.CourseRepository;



// @RestController
// @RequestMapping("api")
// public class InstructorControllers {
   

//      @Autowired
//     private CourseRepository courseRepository; // Assuming you have a repository for Course entity

//     @Autowired
//     private CategoryRepository categoryRepository; // Assuming you have a repository for Category entity

    
//     @GetMapping("/courses")
//     public ModelAndView getAllCourses() {
//         ModelAndView modelAndView = new ModelAndView("courses.html"); // Assuming you have a view named "courses"
//         List<Course> courses = courseRepository.findAll();
//         modelAndView.addObject("courses", courses);
//         return modelAndView;
//     }
//     @GetMapping("/c")
// public ResponseEntity<List<Course>> getAllCourse() {
//     try {
//         List<Course> courses = courseRepository.findAll();
//         return ResponseEntity.ok(courses);
//     } catch (Exception e) {
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//     }
// }


// @PostMapping("/ac")
// public ResponseEntity<Course> addNewCourss(@RequestBody Course course) {
//     // Save the new course to the database
//     Course savedCourse = courseRepository.save(course);
//     // Return the saved course as a ResponseEntity
//     return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
// }

//     @GetMapping("/add-course")
//     public ModelAndView showAddCourseForm() {
//         ModelAndView modelAndView = new ModelAndView("add-course.html"); // Assuming you have a view named "add-course"
//         modelAndView.addObject("course", new Course());
//         List<Category> allc = this.categoryRepository.findAll();
//         //List<Admin> alla = this.adminRepository.findAll();
//        // modelAndView.addObject("alla",alla );
//         modelAndView.addObject("allc",allc);
//         return modelAndView;
//     }

//     @PostMapping("/add-course")
//     public String addNewCourse(@ModelAttribute("course") Course course) {
//         // Save the new course to the database
//         courseRepository.save(course);
//         // Redirect to the page showing all courses
//         return "redirect:/courses";
//     }














//     @GetMapping("/course/{courseId}")
// public ModelAndView showCourseMaterialsForm(@PathVariable Long courseId) {
//     Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
//     ModelAndView modelAndView = new ModelAndView("cm.html");
//     modelAndView.addObject("course", course);
//     return modelAndView;
// }

// @PostMapping("/courses/{courseId}/materials")
// public ResponseEntity<?> addCourseMaterials(@PathVariable Long courseId, @RequestBody List<CourseMaterial> courseMaterials) {
//         Optional<Course> courseOptional = courseRepository.findById(courseId);
//         if (courseOptional.isPresent()) {
//             Course course = courseOptional.get();
//             course.getCourseMaterial().addAll(courseMaterials);
//             courseRepository.save(course);
//             return ResponseEntity.ok().build();
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }

// }
