package com.student.dao;

import com.student.entity.PurchaseCourses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseCourseRepo extends JpaRepository<PurchaseCourses, Long> {
    List<PurchaseCourses> findByCourseId(long courseId);

    List<PurchaseCourses> findByStudentId(long studentId);

}

