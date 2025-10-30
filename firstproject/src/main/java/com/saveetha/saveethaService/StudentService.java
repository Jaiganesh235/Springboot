package com.saveetha.saveethaService;

import com.saveetha.entity.Student;
import com.saveetha.respository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

      public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student createStudent(Student student)
    {
        return studentRepo.save(student);
    }

    public Student getById(int id) {
          return studentRepo.findById(id).get();
    }

    public String deleteStudent(int id) {
          if(studentRepo.findById(id).isEmpty()){
              return "Student not found";
          }
          studentRepo.deleteById(id);
          return "Student deleted successfully";
    }

    public Student updateStudent(int id, Student student) {
          Student existingStudent=studentRepo.findById(id).get();
          if (existingStudent==null){
              return null;
          }
          existingStudent.setBranch(student.getBranch());
          existingStudent.setEmail(student.getEmail());
          existingStudent.setName(student.getName());
          studentRepo.save(existingStudent);
          return existingStudent;
    }
}
