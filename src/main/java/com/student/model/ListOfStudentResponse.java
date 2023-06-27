package com.student.model;
import com.student.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListOfStudentResponse {
    private boolean result;
    private String message;
    private List<Student> studentList;
}

