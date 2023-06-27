package com.student.model;
import com.student.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TeachertResponseObject {
    private boolean result;
    private  String message;
    private Teacher data;
}
