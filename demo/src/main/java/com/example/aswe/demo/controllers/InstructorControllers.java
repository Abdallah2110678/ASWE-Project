package com.example.aswe.demo.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.aswe.demo.models.Category;
import com.example.aswe.demo.models.Course;
import com.example.aswe.demo.models.CourseMaterial;
import com.example.aswe.demo.models.Role;
import com.example.aswe.demo.models.User;
import com.example.aswe.demo.repository.CategoryRepository;
import com.example.aswe.demo.repository.CourseMaterialRepository;
import com.example.aswe.demo.repository.CourseRepository;
import com.example.aswe.demo.service.CourseService;
import com.example.aswe.demo.service.MaterialService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/instructor")
public class InstructorControllers {
   

    @Value("${project.video}")
    private String path;

    @Autowired
    private  CourseService courseService;


    @Autowired
    private MaterialService materialService;

    @Autowired
    private CourseRepository courseRepository;




    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Autowired
    private CategoryRepository categoryRepository ;


    //api for posting course title and description



    
    @PostMapping("createcourse")
    public ResponseEntity<?> save(@RequestParam("categoryId") Long categoryId,@RequestBody Course course) {
        Category category = categoryRepository.findById(categoryId).get();
        course.setCategory(category);
        return new ResponseEntity<Course>(courseService.createPost(course),HttpStatus.OK);
    }



    @GetMapping("/allcourse")
    public ResponseEntity<?> getAllCourse() {
        return new ResponseEntity<List<Course>>(courseService.getAllPost(),HttpStatus.OK);
    }

    @GetMapping("/categories")
public ResponseEntity<List<Category>> getAllCategories() {
    List<Category> categories = categoryRepository.findAll();
    return new ResponseEntity<>(categories, HttpStatus.OK);
}


    @GetMapping("course/{id}")
    public Course getCourseById(@PathVariable("id") Long id) {
        return courseService.getPost(id);
    }

    //api for uploading video after posting titles and description

    // @PostMapping("/post/{id}")
    // public Course uploadVideo(@PathVariable("id") Long id, @RequestParam("video") MultipartFile video)
    // throws IOException{
    //     Course course = courseService.getPost(id);
    //     CourseMaterial courseMaterial = materialService.uploadCourseMaterial(path,video);
    //     course.setVideoName(courseMaterial.getVideoFileName());
    //     Course FinallyUpload= courseService.updatePost(course, id);
    //     return FinallyUpload;
    // }


    @GetMapping("/courses/{userId}")
    public List<Course> getCoursesByUserId(@PathVariable Long userId) {
        return courseRepository.findAllByUserId(userId);
    }

    @PostMapping("/post/{id}")
    public CourseMaterial uploadVideo(@PathVariable("id") Long id, @RequestParam("video") MultipartFile video,
                                        @RequestParam("title") String title )
    throws IOException{
        Course course = courseRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));

         CourseMaterial courseMaterial=materialService.uploadCourseMaterial(path,video);
         courseMaterial.setTitle(title);
         courseMaterial.setCourse(course);
        
        // Save the CourseMaterial
        // return courseMaterialRepository.save(courseMaterial);
        courseMaterial.setVideoFileName(courseMaterial.getVideoFileName());
        // Course FinallyUpload= courseService.updatePost(course, id);
        // return FinallyUpload;
        return courseMaterialRepository.save(courseMaterial);
    }



    @GetMapping(value="/play/{id}/{im}", produces = MediaType.ALL_VALUE)
    public void playVideo(@PathVariable("id") Long id, @PathVariable("im") Long im, HttpServletResponse response) throws IOException {
        // Retrieve the course by its ID
        Optional<Course> optionalCourse = courseRepository.findById(id);
        
        // Check if the course exists
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            
            // Find the course material by its ID
            Optional<CourseMaterial> optionalCourseMaterial = course.getCourseMaterials().stream()
                                                    .filter(cm -> cm.getId().equals(im))
                                                    .findFirst();
    
            // Check if the course material exists
            if (optionalCourseMaterial.isPresent()) {
                CourseMaterial courseMaterial = optionalCourseMaterial.get();
                try {
                    // Get the input stream for the video file
                    InputStream resource = materialService.getCourseMaterial(path, courseMaterial.getVideoFileName(), id);
                    
                    // Set response content type
                    response.setContentType(MediaType.ALL_VALUE);
                    
                    // Copy the file content to the response output stream
                    StreamUtils.copy(resource, response.getOutputStream());
                } catch (IOException e) {
                    // Handle any IOException
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    e.printStackTrace();
                }
            }
        } }

    
    // @GetMapping(value="/play/{id}/{im}", produces = MediaType.ALL_VALUE )
    // public void playVideo(@PathVariable("id") Long id,@PathVariable("im") Long im, HttpServletResponse response) throws IOException{


    //     CourseMaterial course = courseMaterialRepository.findCourseMaterialByIdAndCourseId(im,id);

        
    //     InputStream resource =materialService.getCourseMaterial(path,course.getVideoFileName(), id);
    //     response.setContentType(MediaType.ALL_VALUE);
    //     StreamUtils.copy(resource,response.getOutputStream());
    // }
   



    @DeleteMapping("/delete/course/{id}")
public ResponseEntity<?> deleteCourse(@PathVariable("id") Long id) {
    Optional<Course> optionalCourse = courseRepository.findById(id);
    if (optionalCourse.isPresent()) {
        Course course = optionalCourse.get();
        try {
            List<CourseMaterial> courseMaterials = course.getCourseMaterials();
            for (CourseMaterial material : courseMaterials) {
                courseMaterialRepository.delete(material);
            }
            courseRepository.delete(course);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    } else {
        return ResponseEntity.notFound().build();
    }
}



     @DeleteMapping("/{courseId}/material/{materialId}")
    public ResponseEntity<String> deleteCourseMaterial( @PathVariable("courseId") Long courseId, @PathVariable("materialId") Long materialId)
    throws Exception{
        // Check if the Course exists
        if (!courseRepository.existsById(courseId)) {
            return new ResponseEntity<>("Course not found with id: " + courseId, HttpStatus.NOT_FOUND);
        }

        CourseMaterial course = courseMaterialRepository.findCourseMaterialByIdAndCourseId(materialId,courseId);
        courseMaterialRepository.delete(course);
        // // Delete the CourseMaterial from the Course
        // courseMaterialRepository.deleteCourseMaterialByIdAndCourseId(materialId, courseId);

        return new ResponseEntity<>("CourseMaterial deleted successfully", HttpStatus.OK);
    }







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

}
