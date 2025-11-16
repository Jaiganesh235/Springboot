package com.saveetha.saveethaService;

import com.saveetha.entity.Student;
import com.saveetha.respository.StudentRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
                                          //Mocking object
    @Mock
    StudentRepo studentRepo;

    @InjectMocks
    StudentServiceImp studentService;


    @Test
    void createStudent() {
        Student std=new Student();
        std.setId(1);
        std.setBranch("AIML");
        std.setName("Jai");
        std.setPassword("jai");

        Mockito.when(studentRepo.save(std)).thenReturn(std);

        Student saved=studentService.createStudent(std);

        Assertions.assertEquals("Jaikmj",saved.getName());
        Assertions.assertEquals("AIML",saved.getBranch());
        Assertions.assertEquals("jaig",saved.getPassword());
    }
}                                                              //preparation