package com.student.model;

import com.student.entity.PurchaseCourses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseCoursesResponseWithName {
    private String courseName;
    private int numOfCourses;
    private List<PurchaseCourses> purchaseCoursesList;
}
