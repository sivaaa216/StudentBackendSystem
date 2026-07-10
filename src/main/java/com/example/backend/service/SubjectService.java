package com.example.backend.service;

import com.example.backend.dto.SubjectDTO;
import com.example.backend.model.Student;
import com.example.backend.model.Subject;
import com.example.backend.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
