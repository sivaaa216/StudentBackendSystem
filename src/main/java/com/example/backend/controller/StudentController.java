package com.example.backend.controller;

import com.example.backend.dto.StudentDTO;
import com.example.backend.model.Student;
import com.example.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/getAll")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/getStudent/{id}")
    public Student getByStudentId(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/insertData")
    public Student createData(@RequestBody StudentDTO studentDTO) {
        return studentService.saveData(studentDTO);
    }

    @DeleteMapping("/deleteData/{id}")
    public void deleteData(@PathVariable int id) {
        studentService.deleteData(id);
    }

    @GetMapping("/findByName/{name}")
    public Student findByName(@PathVariable String name) {
        return studentService.findByName(name);
    }

    @PostMapping("/InsertDataFromFile")
    public List<Student> insertAllData(@RequestParam("file") MultipartFile file) {
        return studentService.saveAllData(file);
    }
    @PostMapping("/insertJson")
    public List<Student> insertJson(@RequestParam("file") MultipartFile file) {
        return studentService.saveAllJsonData(file);
    }
}
