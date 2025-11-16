package com.saveetha.DTO;

import com.saveetha.entity.Teacher;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

  private String name;
  private String email;
  Teacher teacherid;
}
