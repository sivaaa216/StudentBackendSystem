package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sub_id;
    private String sub_name;
    private Double mark;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
