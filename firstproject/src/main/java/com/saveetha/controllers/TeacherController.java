package com.saveetha.controllers;

import com.saveetha.DTO.TeacherDTO;
import com.saveetha.entity.Teacher;
import com.saveetha.saveethaService.TeacherServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api")
public class TeacherController {

    @Autowired
    TeacherServiceImp TeacherService;


    @GetMapping("/welcomeee")
    public String welocme(){
        return "Welcome to Saveetha Engineering College";
    }

    @GetMapping("/addee")
    public int addition()
    {
        return (10+20);
    }

    @GetMapping("getallTeachers")
    public List<TeacherDTO> getAllTeachers(){
        return TeacherService.getAllTeachers();
    }

    @PostMapping("/createTeacher")
    public Teacher createTeacher(@Valid @RequestBody Teacher Teacher)
    {
        return TeacherService.createTeacher(Teacher);
    }

    @GetMapping("/getbytechid/{id}")
    public TeacherDTO getById(@PathVariable int id){
        return TeacherService.getbyId(id);
    }

    @DeleteMapping("deleteTeacher/{id}")
    public String deleteTeacher(@PathVariable int id){
        return TeacherService.deleteTeacher(id);
    }

    @PutMapping("updateTeacher/{id}")
    public Teacher updateTeacher(@PathVariable int id, @RequestBody Teacher Teacher){
        return TeacherService.updateTeacher(id,Teacher);
    }
    @GetMapping("getbytechname/{name}")
    public List<Teacher> getbyname(@PathVariable String name){
        return TeacherService.getname(name);
    }

    @PostMapping("postalltech")
    public List<Teacher> postallusers(@RequestBody List<Teacher> TeacherList) {
        return TeacherService.postallusers(TeacherList);
    }
}
