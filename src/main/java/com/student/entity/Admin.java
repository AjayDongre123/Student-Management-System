package com.student.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adminId;

    private String adminName;

    private long studentId;
    private String studentName;

    private long departmentId;

    private String departmentName;

    private String email;

    private String city;

    private String mobile;

    private long totalStudent;

    private long teacherId;
    private String teacherName;






}


