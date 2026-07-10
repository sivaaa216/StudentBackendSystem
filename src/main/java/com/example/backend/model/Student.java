package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String department;
    private String email;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    List<Subject> subjects = new ArrayList<>();
}
