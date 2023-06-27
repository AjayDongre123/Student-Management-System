package com.student.service;

import com.student.dao.BranchRepository;
import com.student.dao.DepartmentRepository;
import com.student.dao.StudentRepository;
import com.student.entity.Branch;
import com.student.entity.Student;
import com.student.model.BranchObjectResponse;
import com.student.model.ListOfBranchResponse;
import com.student.model.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    StudentRepository studentRepository;

    public ResponseEntity addBranch(long departmentId, String branchName) {
        Branch branch = new Branch();

        if (departmentRepository.existsById(departmentId)) {
            List<Branch> byDepartmentId = branchRepository.findByDepartmentId(departmentId);
            for (Branch branchdetails : byDepartmentId) {
                String branchOnName = branchdetails.getBranchName();
                if (branchOnName.equals(branchName) && branchdetails.getDepartmentId() == departmentId) {
                    return new ResponseEntity(new MessageResponse(false, "Branch name is already declared!!")
                            , HttpStatus.ALREADY_REPORTED);
                }
                if (branchRepository.existsByBranchName(branchName) && branchdetails.getDepartmentId() == departmentId) {
                    return new ResponseEntity(new MessageResponse(false, "Branch name is already declared!!"),
                            HttpStatus.ALREADY_REPORTED);
                }
            }
            branch.setDepartmentId(departmentId);
            branch.setBranchName(branchName);
            branch.setTotalStudent(0);
            branchRepository.save(branch);
            branchRepository.findById(branch.getBranchId());


            return new ResponseEntity<>("Successfully added branch", HttpStatus.OK);
        }

        return new ResponseEntity<>(" department Id not exist!! Not added branch", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity updateBranch(long branchId, String branchName) {
        if (branchRepository.existsById(branchId)) {
            Branch branch = branchRepository.findById(branchId).get();
            branch.setBranchId(branchId);
            branch.setBranchName(branchName);
            branchRepository.save(branch);
            return new ResponseEntity<>(new MessageResponse(true, "updated branchName!!"), HttpStatus.OK);

        }
        return new ResponseEntity<>(new MessageResponse(false, "branch Id not exist!!!!branchName not updated!!"), HttpStatus.NOT_FOUND);

    }

    public ResponseEntity deleteBranch(long branchId) {
        if (branchRepository.existsById(branchId)) {
            List<Student> listOfStudent = studentRepository.findByBranchId(branchId);
            if (!listOfStudent.isEmpty()) {
                for (Student student : listOfStudent) {
                    studentRepository.deleteById(student.getStudentId());
                }
            } else {
                return new ResponseEntity<>(new MessageResponse(false, "No student here.."), HttpStatus.NOT_FOUND);
            }
            branchRepository.deleteById(branchId);
            return new ResponseEntity<>(new MessageResponse(true, "Successfully deleted!!"), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(new MessageResponse(false, "id not Exist"), HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity getByBranchId(long branchId) {
        if (branchRepository.existsById(branchId)) {
            Branch branch = branchRepository.findById(branchId).get();
            return new ResponseEntity<>(new BranchObjectResponse(true, "Successfully get Data", branch), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MessageResponse(false, "not found data"), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity branchList() {
        List<Branch> all = branchRepository.findAll();
        return new ResponseEntity<>(new ListOfBranchResponse(true, "successfully get all branch", all), HttpStatus.OK);
    }
}

