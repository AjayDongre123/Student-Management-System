package com.student.model;

import com.student.entity.Courses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CourseResponseObject {
    private boolean result;
    private  String message;
    private Courses data;
}
