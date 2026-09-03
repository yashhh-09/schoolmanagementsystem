package com.school.service;

import com.school.entity.Student;
import com.school.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;
    public StudentService(StudentRepository repository) { this.repository = repository; }
    public List<Student> findAll() { return repository.findAll(); }
    public Student findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found: " + id));
    }
    public Student save(Student item) { return repository.save(item); }
    public Student update(Long id, Student item) {
        item.setId(id);
        return repository.save(item);
    }
    public void delete(Long id) { repository.deleteById(id); }
}
