package com.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;

    private String firstName;

    private String lastName;

    private long branchId;

    private long departmentId;

    private String branchName;

    private String departmentName;

    private String email;
    private String city;
    private String mobile;
    private String studentImage;

    private String studentPassword;

}
