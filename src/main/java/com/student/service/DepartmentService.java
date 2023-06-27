package com.student.service;

import com.student.dao.BranchRepository;
import com.student.dao.DepartmentRepository;
import com.student.entity.Branch;
import com.student.entity.Department;
import com.student.model.ListOfDepartmentResponse;
import com.student.model.MessageResponse;
import com.student.model.ResponseDepartmentObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    BranchRepository branchRepository;

    @Autowired
    BranchService branchService;

    public ResponseEntity addDepartment(String departmentName) {
        Department department = new Department();
        List<Department> byDepartmentName = departmentRepository.findByDepartmentName(departmentName);
        for (Department departmentdetails : byDepartmentName) {
            String departmentOnName = departmentdetails.getDepartmentName();
            if (departmentOnName.equals(departmentName)) {
                return new ResponseEntity(new MessageResponse(false, "Department name is already declared!!")
                        , HttpStatus.ALREADY_REPORTED);
            }
        }
        department.setDepartmentName(departmentName);
        departmentRepository.save(department);
        return new ResponseEntity(new MessageResponse(true, "Succesfully added department!!"),
                HttpStatus.OK);


    }


    public ResponseEntity updateDepartment(long departmentId, String departmentName) {
        if (departmentRepository.existsById(departmentId)) {
            Department department = departmentRepository.findById(departmentId).get();
            department.setDepartmentId(departmentId);
            department.setDepartmentName(departmentName);
            departmentRepository.save(department);
            return new ResponseEntity<>(new MessageResponse(true, "successfully updated Department"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MessageResponse(false, "Id not found!!"), HttpStatus.NOT_FOUND);


    }

    public ResponseEntity deleteDepartment(long departmentId) {
        if (departmentRepository.existsById(departmentId)) {
            List<Branch> listOfBranch = branchRepository.findByDepartmentId(departmentId);
            if (!listOfBranch.isEmpty()) {
                for (Branch branch : listOfBranch) {
                    branchService.deleteBranch(branch.getBranchId());

                }
            } else {
                return new ResponseEntity(new MessageResponse(false, "No branch here!!"), HttpStatus.NOT_FOUND);
            }
            departmentRepository.deleteById(departmentId);
            return new ResponseEntity(new MessageResponse(true, "Successfully deleted department id!!"), HttpStatus.OK);

        } else {
            return new ResponseEntity(new MessageResponse(false, " department id not Exist!!"), HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity getDepartmentById(long departmentId) {
        if (departmentRepository.existsById(departmentId)) {
            Department department = departmentRepository.findById(departmentId).get();
            return new ResponseEntity(new ResponseDepartmentObject(true,"Successfully get Data",department), HttpStatus.OK);

        }
        return new ResponseEntity(new MessageResponse(false,"department id not Exist!!"), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity getAllDepartmentList() {
        List<Department> all = departmentRepository.findAll();
        return new ResponseEntity<>(new ListOfDepartmentResponse(true, "Successfully", all), HttpStatus.OK);
    }
}