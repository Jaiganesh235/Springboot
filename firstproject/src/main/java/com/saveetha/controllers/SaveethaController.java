package com.saveetha.controllers;

import com.saveetha.DTO.StudentDTO;
import com.saveetha.entity.Student;
import com.saveetha.saveethaService.StudentServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SaveethaController {

    @Autowired
    StudentServiceImp studentService;


    @GetMapping("/welcome")
    public String welocme(){
        return "Welcome to Saveetha Engineering College";
    }

    @GetMapping("/add")
    public int addition()
    {
        return (10+20);
    }

    @GetMapping("getallstudents")
    public List<StudentDTO> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping("/createstudent")
    public Student createStudent(@Valid @RequestBody Student student)
    {
        return studentService.createStudent(student);
    }

    @GetMapping("/getbyid/{id}")
    public StudentDTO getById(@PathVariable int id){
        return studentService.getById(id);
    }

    @DeleteMapping("deletestudent/{id}")
    public String deleteStudent(@PathVariable int id){
        return studentService.deleteStudent(id);
    }

    @PutMapping("updatestudent/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student){
        return studentService.updateStudent(id,student);
    }
    @GetMapping("getbyname/{name}")
    public List<Student> getbyname(@PathVariable String name){
        return studentService.getname(name);
    }

    @PostMapping("postallusers")
    public List<Student> postallusers(@RequestBody List<Student> studentList) {
        return studentService.postallusers(studentList);
    }
}
