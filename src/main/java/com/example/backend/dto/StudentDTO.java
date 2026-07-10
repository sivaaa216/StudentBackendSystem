package com.example.backend.dto;

import java.util.List;

public class StudentDTO {
    public int id;
    public String name;
    public String department;
    public String email;
    public List<SubjectDTO> subjectDTOs;
}
