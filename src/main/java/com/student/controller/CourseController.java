package com.student.controller;
import com.student.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/addCourse")
    public ResponseEntity addCourse(@RequestParam(name = "courseName") String courseName) {
        return courseService.addCourse(courseName);
    }

    @PutMapping("/updateCourse")
    public ResponseEntity updateBranch(@RequestParam(name = "courseId") long courseId,
                                       @RequestParam(name = "courseName") String courseName) {
        return courseService.updateCourse(courseId, courseName);
    }

    @DeleteMapping("/deleteCourse")
    public ResponseEntity deleteCourse(@RequestParam(name = "courseId") long courseId) {
        return courseService.deleteCourse(courseId);
    }

    @GetMapping("/getByCourseId")
    public ResponseEntity getCourseId(@RequestParam(name = "courseId") long courseId) {
        return courseService.getCourseById(courseId);
    }

    @GetMapping("/getAll")
    public ResponseEntity courseList() {
        return courseService.CourseList();
    }

    @PostMapping("/addPurchaseCourse")
    public ResponseEntity addpurchaseCourse(@RequestParam(name = "studentId") long studentId,
                                            @RequestParam(name = "courseId") long courseId) {
        return courseService.addpurchaseCourse(studentId, courseId);
    }

    @GetMapping("/getAllPurchaseCourse")
    public ResponseEntity purchaseCourseList() {
        return courseService.purchaseCourseList();
    }
}
