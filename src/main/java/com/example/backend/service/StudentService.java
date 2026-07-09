package com.example.backend.service;

import com.example.backend.model.Student;
import com.example.backend.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    public Student saveData(Student student) {
        return studentRepo.save(student);
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepo.findById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public void deleteData(int id) {
        studentRepo.deleteById(id);
    }

    public Student findByName(String name) {
        return studentRepo.findByName(name);
    }

    public List<Student> readFile(MultipartFile file) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
            String content = reader.readLine();
            while ((content = reader.readLine()) != null) {
                Student student = new Student();
                String[] splitContent = content.split(",");
                student.setName(splitContent[0]);
                student.setDepartment(splitContent[1]);
                student.setEmail(splitContent[2]);
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Student> saveAllData(MultipartFile file) {
        List<Student> students = readFile(file);
        return studentRepo.saveAll(students);
    }

    public List<Student> readJsonFile(MultipartFile file) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(
                    file.getInputStream(),
                    new TypeReference<List<Student>>() {}
            );

        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file", e);
        }
    }

    public List<Student> saveAllJsonData(MultipartFile file) {
        List<Student> students = readJsonFile(file);
        return studentRepo.saveAll(students);
    }
}
