package com.student.controller;


import com.student.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {


    @Autowired
    TeacherService teacherService;

    @PostMapping("/addTeacher")
    public ResponseEntity addTeacher(@RequestParam(name = "teacherName") String teacherName,
                                     @RequestParam(name = "branchId") long branchId,
                                     @RequestParam(name = "courseId") long courseId) {

        return teacherService.addTeacher(teacherName, branchId, courseId);
    }

    @PutMapping("/updateTeacher")
    public ResponseEntity updateTeacher(@RequestParam(name = "branchId") long branchId,
                                        @RequestParam(name = "teacherId") long teacherId,
                                        @RequestParam(name = "teacherName") String teacherName) {
        return teacherService.updateTeacher(teacherId, branchId, teacherName);
    }

    @DeleteMapping("/deleteTeacher")
    public ResponseEntity deleteTeacher(@RequestParam(name = "teacherId") long teacherId) {
        return teacherService.deleteTeacher(teacherId);

    }

    @GetMapping("/getByteacherId")
    public ResponseEntity getByTeacherId(@RequestParam(name = "teacherId") long teacherId) {
        return teacherService.getByTeacherId(teacherId);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        return teacherService.getAll();
    }
}

