package com.student.model;
import com.student.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ListOfTeacherResponse {

        private boolean result;
        private String message;
        private List<Teacher> TeacherList;
    }
