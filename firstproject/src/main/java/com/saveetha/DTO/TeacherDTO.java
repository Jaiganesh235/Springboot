package com.saveetha.DTO;

import com.saveetha.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private String name;
    private String email;

}
