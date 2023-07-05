package com.student.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchaseCourses")
public class PurchaseCourses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long purchaseId;
    private String purchaseTransactionId;
    private String courseName;
    private long courseId;
    private long studentId;
    private String studentName;
}
