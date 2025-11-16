package com.saveetha.saveethaService;

import com.saveetha.DTO.TeacherDTO;
import com.saveetha.entity.Teacher;
import com.saveetha.respository.TeacherRepo;
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
public class TeacherServiceTest {

    @Mock
    TeacherRepo TeacherRepo;   // same name as in service

    @InjectMocks
    TeacherServiceImp teacherService;

    @Test
    void createTeacher_shouldSaveAndReturnTeacher() {
        Teacher t = new Teacher();
        t.setId(1);
        t.setBranch("CSE");
        t.setName("Jai");
        t.setEmail("jai@example.com");

        Mockito.when(TeacherRepo.save(t)).thenReturn(t);

        Teacher saved = teacherService.createTeacher(t);

        Assertions.assertEquals("Jai", saved.getName());
        Assertions.assertEquals("CSE", saved.getBranch());
        Assertions.assertEquals("jai@example.com", saved.getEmail());
    }

    @Test
    void getAllTeachers_shouldReturnDtoList() {
        Teacher t1 = new Teacher();
        t1.setId(1);
        t1.setName("Jai");
        t1.setEmail("jai@example.com");

        Teacher t2 = new Teacher();
        t2.setId(2);
        t2.setName("Raj");
        t2.setEmail("raj@example.com");

        List<Teacher> teachers = Arrays.asList(t1, t2);

        Mockito.when(TeacherRepo.findAll()).thenReturn(teachers);

        List<TeacherDTO> result = teacherService.getAllTeachers();

        Assertions.assertEquals(2, result.size());

        TeacherDTO dto1 = result.get(0);
        Assertions.assertEquals("Jai", dto1.getName());
        Assertions.assertEquals("jai@example.com", dto1.getEmail());

        TeacherDTO dto2 = result.get(1);
        Assertions.assertEquals("Raj", dto2.getName());
        Assertions.assertEquals("raj@example.com", dto2.getEmail());
    }

    @Test
    void getbyId_shouldReturnDto_whenTeacherExists() {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setName("Jai");
        teacher.setEmail("jai@example.com");

        Mockito.when(TeacherRepo.findById(1)).thenReturn(Optional.of(teacher));

        TeacherDTO dto = teacherService.getbyId(1);

        Assertions.assertEquals("Jai", dto.getName());
        Assertions.assertEquals("jai@example.com", dto.getEmail());
    }

    @Test
    void deleteTeacher_shouldDelete_whenTeacherExists() {
        Teacher teacher = new Teacher();
        teacher.setId(1);

        Mockito.when(TeacherRepo.findById(1)).thenReturn(Optional.of(teacher));

        String msg = teacherService.deleteTeacher(1);

        Mockito.verify(TeacherRepo).deleteById(1);
        Assertions.assertEquals("Teacher deleted successfully", msg);
    }

    @Test
    void deleteTeacher_shouldReturnNotFound_whenTeacherDoesNotExist() {
        Mockito.when(TeacherRepo.findById(1)).thenReturn(Optional.empty());

        String msg = teacherService.deleteTeacher(1);

        Mockito.verify(TeacherRepo, Mockito.never()).deleteById(Mockito.anyInt());
        Assertions.assertEquals("Teacher not found", msg);
    }

    @Test
    void updateTeacher_shouldUpdateFields_whenTeacherExists() {
        Teacher existing = new Teacher();
        existing.setId(1);
        existing.setBranch("ECE");
        existing.setName("Old Name");
        existing.setEmail("old@example.com");

        Teacher toUpdate = new Teacher();
        toUpdate.setBranch("CSE");
        toUpdate.setName("New Name");
        toUpdate.setEmail("new@example.com");

        Mockito.when(TeacherRepo.findById(1)).thenReturn(Optional.of(existing));
        Mockito.when(TeacherRepo.save(Mockito.any(Teacher.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Teacher updated = teacherService.updateTeacher(1, toUpdate);

        Assertions.assertEquals("CSE", updated.getBranch());
        Assertions.assertEquals("New Name", updated.getName());
        Assertions.assertEquals("new@example.com", updated.getEmail());
    }

    @Test
    void getname_shouldReturnTeachersWithGivenName() {
        Teacher t1 = new Teacher();
        t1.setId(1);
        t1.setName("Jai");

        Teacher t2 = new Teacher();
        t2.setId(2);
        t2.setName("Jai");

        List<Teacher> list = Arrays.asList(t1, t2);

        Mockito.when(TeacherRepo.findByName("Jai")).thenReturn(list);

        List<Teacher> result = teacherService.getname("Jai");

        Assertions.assertEquals(2, result.size());
        Mockito.verify(TeacherRepo).findByName("Jai");
    }

    @Test
    void postallusers_shouldSaveAllTeachers() {
        Teacher t1 = new Teacher();
        t1.setId(1);
        Teacher t2 = new Teacher();
        t2.setId(2);

        List<Teacher> list = Arrays.asList(t1, t2);

        Mockito.when(TeacherRepo.saveAll(list)).thenReturn(list);

        List<Teacher> result = teacherService.postallusers(list);

        Assertions.assertEquals(2, result.size());
        Mockito.verify(TeacherRepo).saveAll(list);
    }
}
