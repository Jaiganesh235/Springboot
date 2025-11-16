package com.saveetha.respository;

import com.saveetha.entity.Student;
import com.saveetha.entity.Teacher;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepo extends JpaRepository<Teacher, Integer> {

    List<Teacher> findByName(String name);

}
