package com.saveetha.saveethaService;

import com.saveetha.DTO.StudentDTO;
import com.saveetha.entity.Student;
import com.saveetha.respository.StudentRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    StudentRepo studentRepo;

    @InjectMocks
    StudentServiceImp studentService;

    @Test
    void createStudent() {
        Student std = new Student();
        std.setId(1);
        std.setBranch("AIML");
        std.setName("Jai");
        std.setPassword("jai");

        Mockito.when(studentRepo.save(std)).thenReturn(std);

        Student saved = studentService.createStudent(std);

        Assertions.assertEquals("Jai", saved.getName());
        Assertions.assertEquals("AIML", saved.getBranch());
        Assertions.assertEquals("jai", saved.getPassword());
    }

    @Test
    void getAllStudents_shouldReturnDtoList() {
        Student s1 = new Student();
        s1.setId(1);
        s1.setName("Jai");
        s1.setEmail("jai@example.com");


        Student s2 = new Student();
        s2.setId(2);
        s2.setName("Raj");
        s2.setEmail("raj@example.com");


        List<Student> students = Arrays.asList(s1, s2);

        Mockito.when(studentRepo.findAll()).thenReturn(students);

        List<StudentDTO> result = studentService.getAllStudents();

        Assertions.assertEquals(2, result.size());

        StudentDTO dto1 = result.get(0);
        Assertions.assertEquals("Jai", dto1.getName());
        Assertions.assertEquals("jai@example.com", dto1.getEmail());
        Assertions.assertEquals(101, dto1.getTeacherid());

        StudentDTO dto2 = result.get(1);
        Assertions.assertEquals("Raj", dto2.getName());
        Assertions.assertEquals("raj@example.com", dto2.getEmail());
        Assertions.assertEquals(102, dto2.getTeacherid());
    }

    @Test
    void entToDTO_shouldMapFieldsCorrectly() {
        Student student = new Student();
        student.setId(1);
        student.setName("Jai");
        student.setEmail("jai@example.com");


        StudentDTO dto = studentService.entToDTO(student);

        Assertions.assertEquals("Jai", dto.getName());
        Assertions.assertEquals("jai@example.com", dto.getEmail());
        Assertions.assertEquals(500, dto.getTeacherid());
        // If you later include id in DTO mapping, assert that too.
    }

    @Test
    void getById_shouldReturnDto_whenStudentExists() {
        Student student = new Student();
        student.setId(1);
        student.setName("Jai");
        student.setEmail("jai@example.com");


        Mockito.when(studentRepo.findById(1)).thenReturn(Optional.of(student));

        StudentDTO dto = studentService.getById(1);

        Assertions.assertEquals("Jai", dto.getName());
        Assertions.assertEquals("jai@example.com", dto.getEmail());
        Assertions.assertEquals(200, dto.getTeacherid());
    }

    @Test
    void deleteStudent_shouldDelete_whenStudentExists() {
        Student student = new Student();
        student.setId(1);

        Mockito.when(studentRepo.findById(1)).thenReturn(Optional.of(student));

        String msg = studentService.deleteStudent(1);

        Mockito.verify(studentRepo).deleteById(1);
        Assertions.assertEquals("Student deleted successfully", msg);
    }

    @Test
    void deleteStudent_shouldReturnNotFound_whenStudentDoesNotExist() {
        Mockito.when(studentRepo.findById(1)).thenReturn(Optional.empty());

        String msg = studentService.deleteStudent(1);

        Mockito.verify(studentRepo, Mockito.never()).deleteById(Mockito.anyInt());
        Assertions.assertEquals("Student not found", msg);
    }

    @Test
    void updateStudent_shouldUpdateFields_whenStudentExists() {
        Student existing = new Student();
        existing.setId(1);
        existing.setBranch("CSE");
        existing.setName("Old Name");
        existing.setEmail("old@example.com");

        Student toUpdate = new Student();
        toUpdate.setBranch("AIML");
        toUpdate.setName("New Name");
        toUpdate.setEmail("new@example.com");

        Mockito.when(studentRepo.findById(1)).thenReturn(Optional.of(existing));
        // make save return what it receives
        Mockito.when(studentRepo.save(Mockito.any(Student.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Student updated = studentService.updateStudent(1, toUpdate);

        Assertions.assertEquals("AIML", updated.getBranch());
        Assertions.assertEquals("New Name", updated.getName());
        Assertions.assertEquals("new@example.com", updated.getEmail());
    }

    @Test
    void getname_shouldReturnStudentsWithGivenName() {
        Student s1 = new Student();
        s1.setId(1);
        s1.setName("Jai");

        Student s2 = new Student();
        s2.setId(2);
        s2.setName("Jai");

        List<Student> list = Arrays.asList(s1, s2);

        Mockito.when(studentRepo.findByName("Jai")).thenReturn(list);

        List<Student> result = studentService.getname("Jai");

        Assertions.assertEquals(2, result.size());
        Mockito.verify(studentRepo).findByName("Jai");
    }

    @Test
    void postallusers_shouldSaveAllStudents() {
        Student s1 = new Student();
        s1.setId(1);
        Student s2 = new Student();
        s2.setId(2);

        List<Student> list = Arrays.asList(s1, s2);

        Mockito.when(studentRepo.saveAll(list)).thenReturn(list);

        List<Student> result = studentService.postallusers(list);

        Assertions.assertEquals(2, result.size());
        Mockito.verify(studentRepo).saveAll(list);
    }
}
