package com.saveetha.saveethaService;

import com.saveetha.DTO.TeacherDTO;
import com.saveetha.entity.Teacher;
import com.saveetha.respository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImp implements TeacherService {

    @Autowired
    TeacherRepo TeacherRepo;


    public List<TeacherDTO> getAllTeachers(){
        List<Teacher> teachers = TeacherRepo.findAll();
        List<TeacherDTO> teacherDTOS = new ArrayList<>();

         teachers.stream().forEach(teacher -> {
            TeacherDTO dto = new TeacherDTO();
            // dto.setId(student.getId());  // if needed
            dto.setName(teacher.getName());
            dto.setEmail(teacher.getEmail());
             teacherDTOS.add(dto);

         });
         return teacherDTOS;
    }
//    public List<Teacher> getAllTeachers() {
//        return TeacherRepo.findAll();
//    }

    public Teacher createTeacher(Teacher Teacher)
    {
        return TeacherRepo.save(Teacher);
    }

//    public Teacher getById(int id) {
//        return TeacherRepo.findById(id).get();
//    }
      public TeacherDTO getbyId(int id){
          Teacher existingTeacher= TeacherRepo.findById(id).get();
          TeacherDTO teacherDTO=new TeacherDTO();
//        studentDTO.setId(existingStudent.getId());
          teacherDTO.setName(existingTeacher.getName());
          teacherDTO.setEmail(existingTeacher.getEmail());
         // teacherDTO.setTeacherid(existingStudent.getTeacherid());
          return teacherDTO;
      }

    public String deleteTeacher(int id) {
        if(TeacherRepo.findById(id).isEmpty()){
            return "Teacher not found";
        }
        TeacherRepo.deleteById(id);
        return "Teacher deleted successfully";
    }

    public Teacher updateTeacher(int id, Teacher Teacher) {
        Teacher existingTeacher=TeacherRepo.findById(id).get();
        if (existingTeacher==null){
            return null;
        }
        existingTeacher.setBranch(Teacher.getBranch());
        existingTeacher.setEmail(Teacher.getEmail());
        existingTeacher.setName(Teacher.getName());
        TeacherRepo.save(existingTeacher);
        return existingTeacher;
    }

    public List<Teacher> getname(String name) {
        return TeacherRepo.findByName(name);
    }
    public List<Teacher> postallusers(List<Teacher> TeacherList) {
        return TeacherRepo.saveAll(TeacherList);
    }
}
