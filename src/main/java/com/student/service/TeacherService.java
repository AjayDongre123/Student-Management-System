package com.student.service;

import com.student.dao.BranchRepository;
import com.student.dao.CourseRepository;
import com.student.dao.DepartmentRepository;
import com.student.dao.TeacherRepository;
import com.student.entity.Teacher;
import com.student.model.ListOfTeacherResponse;
import com.student.model.MessageResponse;
import com.student.model.TeachertResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    CourseRepository courseRepository;

    public ResponseEntity addTeacher(String teacherName, long branchId, long courseId) {
        Teacher teacher = new Teacher();
        if (branchRepository.existsById(branchId)) {
            if (courseRepository.existsById(courseId)) {
                teacher.setTeacherName(teacherName);
                teacher.setBranchId(branchId);
                teacher.setCourseId(courseId);
                teacherRepository.save(teacher);

                return new ResponseEntity(new MessageResponse(true, "successfully added teacher"), HttpStatus.OK);
            } else
                return new ResponseEntity(new MessageResponse(false, "courseId not Exist!!!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(new MessageResponse(false, "branchId not exist!!!"), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity updateTeacher(long teacherId, long branchId, String teacherName) {
        if (branchRepository.existsById(branchId)) {
            if (teacherRepository.existsById(teacherId)) {
                Teacher teacher = teacherRepository.findById(teacherId).get();
                teacher.setTeacherName(teacherName);
                teacher.setBranchId(branchId);
                teacherRepository.save(teacher);
            } else {
                return new ResponseEntity(new MessageResponse(false, "teacher id not Exist!!!!!!"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(new MessageResponse(true, "Successfully updated teacherName!!!"), HttpStatus.OK);
        }
        return new ResponseEntity(new MessageResponse(false, "branchId not Exist!!!can't updated teacherName!!!"), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity deleteTeacher(long teacherId) {
        if (teacherRepository.existsById(teacherId)) {
            teacherRepository.deleteById(teacherId);
            return new ResponseEntity(new MessageResponse(true, "Successfully deleted"), HttpStatus.OK);
        }
        return new ResponseEntity(new MessageResponse(false, "teacherId not Exist!!!"), HttpStatus.NOT_FOUND);
    }
    public ResponseEntity getByTeacherId(long teacherId) {
        if (teacherRepository.existsById(teacherId)) {
            Teacher teacher = teacherRepository.findById(teacherId).get();
            return new ResponseEntity(new TeachertResponseObject(true,"Successfully get data",teacher), HttpStatus.OK);
        }
        return new ResponseEntity(new MessageResponse(false,"teacherId not Exist!!!! can't fount data"), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity getAll(){
        List<Teacher> all = teacherRepository.findAll();
        return new ResponseEntity(new ListOfTeacherResponse
                (true,"Succcessfully retrive",all),HttpStatus.OK);
    }

}







