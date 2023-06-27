package com.student.model;

import com.student.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDepartmentObject {
    private boolean result;
    private String message;

    private Department data;

}
