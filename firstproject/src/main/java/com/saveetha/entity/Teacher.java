package com.saveetha.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @NotBlank
    private String name;

    @NotBlank
    private String branch;

    @Email
    @NotBlank
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "teacherid", cascade = CascadeType.ALL)
            @JsonIgnore
    List<Student> studentid;

}
