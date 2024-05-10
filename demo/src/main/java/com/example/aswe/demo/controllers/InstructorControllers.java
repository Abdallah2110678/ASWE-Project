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
import com.example.aswe.demo.repository.UserRepository;
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
    private CourseService courseService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private UserRepository userRepository;

    @PostMapping("createcourse")
    public ResponseEntity<?> save(@RequestParam("categoryId") Long categoryId,
     @RequestParam("useId") Long useId,
     @RequestBody Course course) {
        Category category = categoryRepository.findById(categoryId).get();
        User user =  userRepository.findById(useId).get();
        course.setUser(user);
        course.setCategory(category);
        return new ResponseEntity<Course>(courseService.createPost(course), HttpStatus.OK);
    }

    @GetMapping("/allcourse")
    public ResponseEntity<?> getAllCourse() {
        return new ResponseEntity<List<Course>>(courseService.getAllPost(), HttpStatus.OK);
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

    @GetMapping("/courses/{userId}")
    public List<Course> getCoursesByUserId(@PathVariable Long userId) {
        return courseRepository.findAllByUserId(userId);
    }

    @PostMapping("/post/{id}")
    public CourseMaterial uploadVideo(@PathVariable("id") Long id, @RequestParam("video") MultipartFile video,
            @RequestParam("title") String title)
            throws IOException {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));
        CourseMaterial courseMaterial = materialService.uploadCourseMaterial(path, video);
        courseMaterial.setTitle(title);
        courseMaterial.setCourse(course);
        courseMaterial.setVideoFileName(courseMaterial.getVideoFileName());
        return courseMaterialRepository.save(courseMaterial);
    }

    @GetMapping(value = "/play/{id}/{im}", produces = MediaType.ALL_VALUE)
    public void playVideo(@PathVariable("id") Long id, @PathVariable("im") Long im, HttpServletResponse response)
            throws IOException {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            Optional<CourseMaterial> optionalCourseMaterial = course.getCourseMaterials().stream()
                    .filter(cm -> cm.getId().equals(im))
                    .findFirst();
            if (optionalCourseMaterial.isPresent()) {
                CourseMaterial courseMaterial = optionalCourseMaterial.get();
                try {
                    InputStream resource = materialService.getCourseMaterial(path, courseMaterial.getVideoFileName(),
                            id);
                    response.setContentType(MediaType.ALL_VALUE);
                    StreamUtils.copy(resource, response.getOutputStream());
                } catch (IOException e) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    e.printStackTrace();
                }
            }
        }
    }

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
    public ResponseEntity<String> deleteCourseMaterial(@PathVariable("courseId") Long courseId,
            @PathVariable("materialId") Long materialId)
            throws Exception {
        if (!courseRepository.existsById(courseId)) {
            return new ResponseEntity<>("Course not found with id: " + courseId, HttpStatus.NOT_FOUND);
        }

        CourseMaterial course = courseMaterialRepository.findCourseMaterialByIdAndCourseId(materialId, courseId);
        courseMaterialRepository.delete(course);
        return new ResponseEntity<>("CourseMaterial deleted successfully", HttpStatus.OK);
    }
}
