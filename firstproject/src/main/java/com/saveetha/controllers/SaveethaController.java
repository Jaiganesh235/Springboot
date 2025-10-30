package com.saveetha.controllers;

import com.saveetha.entity.Student;
import com.saveetha.saveethaService.StudentService;
import jdk.javadoc.doclet.StandardDoclet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SaveethaController {

    @Autowired
    StudentService studentService;

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
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping("/createstudent")
    public Student createStudent(@RequestBody Student student)
    {
        return studentService.createStudent(student);
    }

    @GetMapping("/getbyid/{id}")
    public Student getById(@PathVariable int id){
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
}
