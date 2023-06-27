package com.student.controller;

import com.student.entity.Branch;
import com.student.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branch")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @PostMapping("/addBranch")
    public ResponseEntity addBranch(@RequestParam(name = "departmentId") long departmentId,
                                    @RequestParam(name = "branchName") String branchName) {

        return branchService.addBranch(departmentId, branchName);
    }

    @PutMapping("/updateBranch")
    public ResponseEntity updateBranch(@RequestParam(name = "branchId") long branchId,
                                       @RequestParam(name = "branchName") String branchName) {
        return branchService.updateBranch(branchId, branchName);
    }
//this is next comment
    @DeleteMapping("/deleteBranch")
    public ResponseEntity deleteBranch(@RequestParam(name = "branchId") long branchId) {
        return branchService.deleteBranch(branchId);
    }
    @GetMapping("/getByBranchId")
    public ResponseEntity getBranchById(@RequestParam(name = "branchId") long branchId)
    {
        return branchService.getByBranchId(branchId);
    }

    @GetMapping("/getall")
    public ResponseEntity branchList()
    {
        return branchService.branchList();
    }
}



