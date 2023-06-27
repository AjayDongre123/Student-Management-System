package com.student.controller;

import com.student.dao.StudentRepository;
import com.student.entity.Student;
import com.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/addStudent")
    public ResponseEntity addStudent(@RequestParam(name = "firstName") String firstName,
                                     @RequestParam(name = "lastname") String lastName,
                                     @RequestParam(name = "branchId") long branchId,
                                     @RequestParam(name = "departmentId") long departmentId,
                                     @RequestParam(name = "email") String email,
                                     @RequestParam(name = "city") String city,
                                     @RequestParam(name = "mobile") String mobile,
                                     @RequestParam(name = "studentImage") MultipartFile studentImage) throws IOException {
        return studentService.addStudent(firstName, lastName, branchId, departmentId, email, city, mobile, studentImage);
    }

    @PutMapping("/updateStudent")
    public ResponseEntity updateStudent(@RequestParam(name = "studentId") long studentId,
                                        @RequestParam(name = "firstName") String firstName,
                                        @RequestParam(name = "lastName") String lastName,
                                        @RequestParam(name = "branchId") long branchId,
                                        @RequestParam(name = "departmentId") long departmentId,
                                        @RequestParam(name = "email") String email,
                                        @RequestParam(name = "city") String city,
                                        @RequestParam(name = "studentImage") MultipartFile studentImage) throws IOException {
        return studentService.updateStudent(studentId, firstName, lastName, branchId, departmentId, email, city, studentImage);
    }

    @DeleteMapping("/deleteStudent")
    public ResponseEntity deleteStudent(@RequestParam(name = "studentId") long studentId) {
        return studentService.deleteStudent(studentId);
    }

    @GetMapping("/getByStudentId")
    public ResponseEntity getByStudentId(@RequestParam(name = "studentId") long studentId) {
        return studentService.getByStudentId(studentId);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        return studentService.getAll();
    }

    @PostMapping("/addStudentImage")
    public ResponseEntity addStudentImage(@RequestParam(name = "studentid") long studentId,
                                          @RequestParam(name = "studentImage") MultipartFile studentImage) throws IOException {
        return studentService.addStudentImage(studentId, studentImage);
    }

    @PostMapping("/setPassword")
    public ResponseEntity setStudentPassword(@RequestParam(name = "sEmail") String userEmail,
                                             @RequestParam(name = "sPassword") String userPassword) {
        return studentService.setPassword(userEmail, userPassword);
    }

    @PostMapping("/updatePassword")
    public ResponseEntity updatePassword(@RequestParam(name = "studentEmail") String sEmail,
                                         @RequestParam(name = "newPwd") String newPassword,
                                         @RequestParam(name = "oldpwd") String oldPassword) {
        return studentService.updatePassword(sEmail, oldPassword, newPassword);
    }

    @GetMapping("/loginStudent")
    public ResponseEntity loginStudent(@RequestParam(name = "sEmail") String email, @RequestParam(name = "password") String pwd) {

        return studentService.loginStudentByEmail(email, pwd);
    }


}
















