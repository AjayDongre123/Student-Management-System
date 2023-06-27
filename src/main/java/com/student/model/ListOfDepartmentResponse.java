package com.student.model;

import com.student.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListOfDepartmentResponse {
    private boolean result;
    private String message;
    private List<Department> departmentList;
}
