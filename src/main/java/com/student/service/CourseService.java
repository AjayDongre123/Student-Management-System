package com.student.service;

import com.student.dao.CourseRepository;
import com.student.dao.PurchaseCourseRepo;
import com.student.dao.StudentRepository;
import com.student.entity.Courses;
import com.student.entity.PurchaseCourses;
import com.student.entity.Student;
import com.student.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    PurchaseCourseRepo purchaseCoursesRepo;

    public ResponseEntity addCourse(String courseName) {
        Courses course = new Courses();
        List<Courses> byCourseName = courseRepository.findByCourseName(courseName);
        for (Courses coursedetails : byCourseName) {
            String courseOnName = coursedetails.getCourseName();
            if (courseOnName.equals(courseName)) {
                return new ResponseEntity(new MessageResponse(false, "Course Name is already present"), HttpStatus.NOT_FOUND);
            }
        }
        course.setCourseName(courseName);
        courseRepository.save(course);
        return new ResponseEntity(new MessageResponse(true, "Successfully course added"), HttpStatus.OK);

    }

    public ResponseEntity updateCourse(long courseId, String courseName) {
        if (courseRepository.existsById(courseId)) {
            Courses course = courseRepository.findById(courseId).get();
            course.setCourseId(courseId);
            course.setCourseName(courseName);
            courseRepository.save(course);

            List<PurchaseCourses> byCourseId = purchaseCoursesRepo.findByCourseId(courseId);
            for (PurchaseCourses purchaseCourses : byCourseId) {
                purchaseCourses.setCourseName(course.getCourseName());
                purchaseCoursesRepo.save(purchaseCourses);
            }
            return new ResponseEntity<>(new MessageResponse(true, "updated courseName!!"), HttpStatus.OK);

        }
        return new ResponseEntity<>(new MessageResponse(false, "courseName not updated!!"), HttpStatus.NOT_FOUND);

    }

    public ResponseEntity deleteCourse(long courseId) {
        if (courseRepository.existsById(courseId)) {
            courseRepository.deleteById(courseId);
            return new ResponseEntity<>(new MessageResponse(true, "Successfully delete "), HttpStatus.OK);

        }
        return new ResponseEntity<>(new MessageResponse(false, "id is not exist"), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity getCourseById(long courseId) {
        if (courseRepository.existsById(courseId)) {

            Courses courses = courseRepository.findById(courseId).get();
            return new ResponseEntity(new CourseResponseObject(true,"Successfully get data",courses),HttpStatus.OK);
        }
        return new ResponseEntity(new MessageResponse(true,"courseId not exist!!!so cannot retrive data"),HttpStatus.NOT_FOUND);
    }

    public ResponseEntity CourseList() {
        List<Courses> all = courseRepository.findAll();
        return new ResponseEntity(new ListOfCourseResponse(true, "Successfully", all), HttpStatus.OK);
    }

    public ResponseEntity addpurchaseCourse(long studentId, long courseId) {
        PurchaseCourses purchaseCourses = new PurchaseCourses();
        if (studentRepository.existsById(studentId)) {
            if (courseRepository.existsById(courseId)) {
                Student student = studentRepository.findById(studentId).get();
                purchaseCourses.setStudentId(studentId);
                purchaseCourses.setStudentName(student.getFirstName());
                Courses courses = courseRepository.findById(courseId).get();
                purchaseCourses.setCourseId(courseId);
                purchaseCourses.setCourseName(courses.getCourseName());
                Random r = new Random();
                String temp = "";
                for (int i = 0; i < 5; i++) {
                    char c = (char) (r.nextInt(26) + 'A');//5 times letters print
                    int num = r.nextInt(9);//5 times numbers print
                    temp += c + "" + num;
                }
                purchaseCourses.setPurhaseTransactionId(temp);
                purchaseCoursesRepo.save(purchaseCourses);
                return new ResponseEntity(new MessageResponse(true, "Successfully added PurchaseCourse"), HttpStatus.OK);
            } else {
                return new ResponseEntity(new MessageResponse(false, "course id not Exist"), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity(new MessageResponse(false, "Student id not Exist"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity purchaseCourseList() {
        List<PurchaseCourses> allPurchaseData = purchaseCoursesRepo.findAll();
        List<PurchaseCoursesResponseWithName> purchaseCoursesList = new ArrayList<>();
        Set<Long> uniqueId = new HashSet<>();
        for (PurchaseCourses purchaseCourses : allPurchaseData) {

            long courseIdByPurchase = purchaseCourses.getCourseId();
            if (!uniqueId.contains(courseIdByPurchase)) {
                uniqueId.add(courseIdByPurchase);
                Courses courses = courseRepository.findById(courseIdByPurchase).get();
                String courseName = courses.getCourseName();
                List<PurchaseCourses> byCourseId = purchaseCoursesRepo.findByCourseId(courseIdByPurchase);
                int countOfCoursesById = byCourseId.size();
                PurchaseCoursesResponseWithName purchaseCoursesResponseWithName = new PurchaseCoursesResponseWithName
                        (courseName, countOfCoursesById, byCourseId);
                purchaseCoursesList.add(purchaseCoursesResponseWithName);
            }
        }
        return new ResponseEntity(new ListOfPurchaseCourseResponse(true, "Successfully get Data", purchaseCoursesList), HttpStatus.OK);
    }
}





    






