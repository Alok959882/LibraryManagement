package com.example.demo.services;

import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.request.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public void create(StudentCreateRequest studentCreateRequest) {
        Student student=studentCreateRequest.to();
        studentRepository.save(student);
    }

    public Student findStudentByStudentId(int sId){
        return studentRepository.findById(sId).orElse(null);
    }
}
