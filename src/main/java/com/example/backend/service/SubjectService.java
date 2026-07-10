package com.example.backend.service;

import com.example.backend.dto.SubjectDTO;
import com.example.backend.model.Student;
import com.example.backend.model.Subject;
import com.example.backend.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepo subjectRepo;

    @Autowired
    private StudentService studentService;

    public void addSubject(SubjectDTO subjectDTO, int id) {
        Student student = studentService.getStudentById(id);
        List<Subject> subjectList = student.getSubjects();
        Subject subject = new Subject();
        subject.setSub_name(subjectDTO.sub_name);
        subject.setMark(subjectDTO.mark);
        subjectList.add(subject);
        student.setSubjects(subjectList);
        subject.setStudent(student);
        studentService.updateStudent(student);
    }

    public void addSubjectFromFile(MultipartFile file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode subjects = objectMapper.readTree(file.getInputStream());
            for (JsonNode subject : subjects) {
                SubjectDTO subjectDTO = new SubjectDTO();
                subjectDTO.setSub_name(subject.get("sub_name").asText());
                subjectDTO.setMark(subject.get("mark").asDouble());
                addSubject(subjectDTO, subject.get("student_id").asInt());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
