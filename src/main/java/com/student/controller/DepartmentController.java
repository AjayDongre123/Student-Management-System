package com.student.controller;

import com.student.entity.Department;
import com.student.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping("/addDepartment")
    public ResponseEntity addDepartment(@RequestParam(name = "departmentName") String departmentName)
    {
        return departmentService.addDepartment(departmentName);
    }

    @PutMapping("/updateDepartment")
    public ResponseEntity<String> updateDepartment(
            @RequestParam(name = "departmentId") long departmentId,
            @RequestParam(name = "departmentName") String departmentName) {
        return departmentService.updateDepartment(departmentId, departmentName);
    }

    @DeleteMapping("/deleteDepartment")
    public ResponseEntity<String> deleteDepartment(@RequestParam(name = "departmentId") long departmentId) {
        return departmentService.deleteDepartment(departmentId);
    }

    @GetMapping("/getDepartmentById")
    public ResponseEntity<String> getDepartment(@RequestParam(name = "departmentId") long departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @GetMapping("/getAllDepartment")
    public ResponseEntity departmentList() {
        return departmentService.getAllDepartmentList();
    }

}

