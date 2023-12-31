package com.student.dao;

import com.student.entity.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);

    //    boolean existByBranchId(Long branchId);
//    boolean existByStudentId(long studentId);
    List<Student> findByBranchId(long branchId);

    Student findByEmail(String email);

    Student findByEmailAndStudentPassword(String email, String password);

    Student findByFirstNameAndLastName(String firstName, String lastName);

    List<Student> findByFirstNameContaining(String key);

    @Query(value = "select * from student s where s.first_name like %?1%", nativeQuery = true)
    List<Student> findByKeyword(String keyword);

    @Query(value = "select * from student s where s.branch_id like %?1%", nativeQuery = true)
    List<Student> findsByBranchId(long branchId);


}
