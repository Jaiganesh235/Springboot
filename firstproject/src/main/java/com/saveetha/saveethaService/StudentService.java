package com.saveetha.saveethaService;

import com.saveetha.DTO.StudentDTO;
import com.saveetha.entity.Student;

import java.util.List;

public interface StudentService {

    StudentDTO entToDTO(Student student);
    List<StudentDTO> getAllStudents();
    Student createStudent(Student student);
    StudentDTO getById(int id);
    String deleteStudent(int id);
    Student updateStudent(int id, Student student);
    List<Student> getname(String name);
    List<Student> postallusers(List<Student> studentList);
}
