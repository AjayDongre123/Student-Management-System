package com.student.dao;

import com.student.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
//    List<Teacher>findByBranchId(Long branchId);
}
