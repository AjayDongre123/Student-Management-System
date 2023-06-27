package com.student.service;

import com.student.dao.BranchRepository;
import com.student.dao.DepartmentRepository;
import com.student.dao.PurchaseCourseRepo;
import com.student.dao.StudentRepository;
import com.student.entity.Branch;
import com.student.entity.Department;
import com.student.entity.PurchaseCourses;
import com.student.entity.Student;
import com.student.model.ListOfStudentResponse;
import com.student.model.MessageResponse;
import com.student.model.StudentObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    PurchaseCourseRepo purchaseCourseRepo;



    public ResponseEntity addStudent(String firstName, String lastName, long branchId, long departmentId,
                                     String email, String city, String mobile, MultipartFile studentImage) throws IOException {
        Student student = new Student();
        if (branchRepository.existsById(branchId)) {
            if (departmentRepository.existsById(departmentId)) {
                if (studentRepository.existsByEmail(email)) {
                    return new ResponseEntity(new MessageResponse(false, "Student email id already exists"), HttpStatus.NOT_FOUND);
                }
                student.setFirstName(firstName);
                student.setLastName(lastName);
                Branch branch = branchRepository.findById(branchId).get();
                long count = branch.getTotalStudent();
                long l = count + 1;
                branch.setTotalStudent(l);
                branchRepository.save(branch);

                student.setBranchId(branchId);
                student.setDepartmentId(departmentId);
                student.setBranchName(branch.getBranchName());
                Department department = departmentRepository.findById(departmentId).get();
                student.setDepartmentName(department.getDepartmentName());
                student.setEmail(email);
                student.setCity(city);
                student.setMobile(mobile);
                String studentImageName = studentImage.getOriginalFilename();
                student.setStudentImage(studentImageName);
                String imagePath = "C:/Learing springboot/Book Project/Book-Project/src/main/resources/static/bookimages";
                Path path = Paths.get(imagePath + File.separator + studentImageName);
                Files.copy(studentImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                studentRepository.save(student);
                return new ResponseEntity("Successfully added data", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("not added data", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("not added data", HttpStatus.NOT_FOUND);
    }


    public ResponseEntity updateStudent(long studentId, String firstName, String lastName, long branchId,
                                        long departmentId, String email, String city, MultipartFile studentImage) throws IOException {
        if (studentRepository.existsById(studentId)) {
            Student student = studentRepository.findById(studentId).get();
            Department department = departmentRepository.findById(departmentId).get();
            student.setStudentId(studentId);
            student.setDepartmentId(departmentId);
            student.setDepartmentName(department.getDepartmentName());
            Branch branch = branchRepository.findById(branchId).get();
            student.setBranchId(branchId);
            student.setBranchName(branch.getBranchName());
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setEmail(email);
            student.setCity(city);
            if (!studentImage.isEmpty()) {
                String studentImg = studentImage.getOriginalFilename();
                String uniqueName = (LocalDateTime.now()).toString().replace(":", "");
                String studentUniqueImg = uniqueName + studentImg;
                String imagePath = "C:/Learing springboot/Student-Management-System/src/main/resources/static/studentimg";

                Path path = Paths.get(imagePath + File.separator + studentUniqueImg);
                Files.copy(studentImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                student.setStudentImage(studentUniqueImg);
            } else {
                student.setStudentImage(student.getStudentImage());
            }
//            student.setStudentImage(student.getStudentImage());

            studentRepository.save(student);
            List<PurchaseCourses> byStudentId = purchaseCourseRepo.findByStudentId(studentId);
            for(PurchaseCourses purchaseCourses : byStudentId)
            {
                purchaseCourses.setStudentName(student.getFirstName());
                purchaseCourseRepo.save(purchaseCourses);
            }

            return new ResponseEntity(new MessageResponse(true, "successfully Updated Student"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new MessageResponse(false, "id not exist"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity deleteStudent(long studentId) {
        if (studentRepository.existsById(studentId)) {
            Student student = studentRepository.findById(studentId).get();
            long branchId = student.getBranchId();
            Branch branch = branchRepository.findById(branchId).get();
            long count = branch.getTotalStudent();
            long C = count - 1;
            branch.setTotalStudent(C);
            branchRepository.save(branch);
            studentRepository.deleteById(studentId);
            return new ResponseEntity(new MessageResponse(true, "Successfully deleted "), HttpStatus.OK);

        }
        return new ResponseEntity(new MessageResponse(false, "id not Exist!!!Not deleted"), HttpStatus.NOT_FOUND);
    }


    public ResponseEntity getByStudentId(long studentId) {
        if (studentRepository.existsById(studentId)) {
            Student student = studentRepository.findById(studentId).get();
            return new ResponseEntity(new StudentObjectResponse(true,"Successfully get data",student),HttpStatus.OK);
        }
        return new ResponseEntity(new MessageResponse(false, "Student id not exist!!! not found data"), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity getAll() {
        List<Student> all = studentRepository.findAll();
        return new ResponseEntity<>(new ListOfStudentResponse(true, "Successfully", all), HttpStatus.OK);

    }

    public ResponseEntity addStudentImage(long studentId, MultipartFile studentImage) throws IOException {

        Student student = studentRepository.findById(studentId).get();
        String studentImageName = studentImage.getOriginalFilename();
        student.setStudentImage(studentImageName);
        String imagePath = "C:/Learing springboot/Book Project/Book-Project/src/main/resources/static/bookimages";
        Path path = Paths.get(imagePath + File.separator + studentImageName);
        Files.copy(studentImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        studentRepository.save(student);
        return new ResponseEntity<>(new MessageResponse(true, "Successfullly Uploaded Image"),
                HttpStatus.OK);


    }

    public ResponseEntity setPassword(String studentEmail, String studentPassword) {
        if (studentRepository.existsByEmail(studentEmail)) {
            Student student = studentRepository.findByEmail(studentEmail);
            student.setStudentPassword(studentPassword);
            studentRepository.save(student);
        }
        return new ResponseEntity(new MessageResponse(true, "Registered successfully!!"), HttpStatus.OK);
    }

    public ResponseEntity updatePassword(String studentEmail, String oldPassword, String newPassword) {
        if (studentRepository.existsByEmail(studentEmail)) {
            Student student = studentRepository.findByEmail(studentEmail);
            if (student.getStudentPassword().equals(oldPassword)) {
                student.setStudentPassword(newPassword);
                Student save = studentRepository.save(student);
                if (save != null) {
                    return new ResponseEntity(new MessageResponse(true, "Successfully changed!!"), HttpStatus.OK);
                }
                return new ResponseEntity(new MessageResponse(false, "unsuccessfully!!"), HttpStatus.NOT_ACCEPTABLE);

            } else {
                return new ResponseEntity(new MessageResponse(false, "Your old password doesn't match!!"), HttpStatus.NOT_FOUND);
            }
        } else {
            new ResponseEntity(new MessageResponse(false, "Email id not present!!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Run Successfully",HttpStatus.OK);
    }

    public ResponseEntity loginStudentByEmail(String email, String spwd) {
        if (studentRepository.existsByEmail(email)) {
            Student studentData = studentRepository.findByEmailAndStudentPassword(email, spwd);
            if(studentData==null){
                return new ResponseEntity(new MessageResponse(false, "Bad request something goes wrong!!"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(studentData, HttpStatus.OK);
        } else {
            return new ResponseEntity(new MessageResponse(false, "Email id not registered!!"), HttpStatus.NOT_FOUND);
        }
    }

}

