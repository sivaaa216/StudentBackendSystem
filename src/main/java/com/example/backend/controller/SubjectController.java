package com.example.backend.controller;

import com.example.backend.dto.SubjectDTO;
import com.example.backend.model.Subject;
import com.example.backend.service.StudentService;
import com.example.backend.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class SubjectController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private StudentService studentService;

    @PostMapping("addSubject/{id}")
    public void addSubject(@RequestBody SubjectDTO subjectDTO, @PathVariable int id) {
        subjectService.addSubject(subjectDTO, id);
    }

    @PostMapping("subject/File")
    public void addSubjectFromFile(@RequestParam("file")MultipartFile file) {
        subjectService.addSubjectFromFile(file);
    }

    @DeleteMapping("subject/delete/{id}")
    public void deleteSubject(@PathVariable int id) {
        subjectService.removeSubject(id);
        System.out.println("Id deleted Sucessfully!!");
    }
}
