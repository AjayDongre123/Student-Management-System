package com.student.model;

import com.student.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentObjectResponse {
    private boolean result;
    private  String message;
    private Student data;

}
