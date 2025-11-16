package com.saveetha.saveethaService;

import com.saveetha.DTO.StudentDTO;
import com.saveetha.entity.Student;
import com.saveetha.respository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    StudentRepo studentRepo;

    public StudentDTO entToDTO(Student student){

        StudentDTO dto = new StudentDTO();
            // dto.setId(student.getId());  // if needed
            dto.setName(student.getName());
            dto.setEmail(student.getEmail());
            dto.setTeacherid(student.getTeacherid());
            return dto;
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepo.findAll();

        return students.stream().map(student -> {
            StudentDTO dto = new StudentDTO();
            // dto.setId(student.getId());  // if needed
            dto.setName(student.getName());
            dto.setEmail(student.getEmail());
            dto.setTeacherid(student.getTeacherid());
            return dto;
        }).collect(Collectors.toList());
    }

//    public List<StudentDTO> getAllStudents() {
//        // Step 1: Get all students from the database
//        List<Student> students = studentRepo.findAll();
//
//        // Step 2: Create an empty list to store StudentDTO objects
//        List<StudentDTO> dtoList = new ArrayList<>();
//
//        // Step 3: Loop through each student
//        for (Student student : students) {
//            // Create a new StudentDTO object
//            StudentDTO dto = new StudentDTO();
//
//            // Copy the required fields from Student to StudentDTO
//            // dto.setId(student.getId());  // uncomment if needed
//            dto.setName(student.getName());
//            dto.setEmail(student.getEmail());
//            dto.setTeacherid(student.getTeacherid());
//
//            // Add the DTO to the list
//            dtoList.add(dto);
//        }
//
//        // Step 4: Return the final list of DTOs
//        return dtoList;
//    }

    public Student createStudent(Student student)
    {
        return studentRepo.save(student);
    }

    public StudentDTO getById(int id) {
        Student existingStudent= studentRepo.findById(id).get();
        StudentDTO studentDTO=new StudentDTO();
//        studentDTO.setId(existingStudent.getId());
        studentDTO.setName(existingStudent.getName());
        studentDTO.setEmail(existingStudent.getEmail());
        studentDTO.setTeacherid(existingStudent.getTeacherid());
        return studentDTO;
//                  .orElseThrow(()->new ResourceNotFoundException("Student not found with id: "+id));
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

    public List<Student> getname(String name) {
          return studentRepo.findByName(name);
    }
    public List<Student> postallusers(List<Student> studentList) {
        return studentRepo.saveAll(studentList);
    }
}
