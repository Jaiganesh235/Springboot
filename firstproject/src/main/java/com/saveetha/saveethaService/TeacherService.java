package com.saveetha.saveethaService;

import com.saveetha.DTO.TeacherDTO;
import com.saveetha.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<TeacherDTO> getAllTeachers();
    Teacher createTeacher(Teacher Teacher);
    TeacherDTO getbyId(int id);
    String deleteTeacher(int id);
    Teacher updateTeacher(int id, Teacher Teacher);
    List<Teacher> getname(String name);
    List<Teacher> postallusers(List<Teacher> TeacherList);



}
