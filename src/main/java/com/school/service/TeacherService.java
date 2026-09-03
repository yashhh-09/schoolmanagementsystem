package com.school.service;

import com.school.entity.Teacher;
import com.school.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository repository;
    public TeacherService(TeacherRepository repository) { this.repository = repository; }
    public List<Teacher> findAll() { return repository.findAll(); }
    public Teacher findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found: " + id));
    }
    public Teacher save(Teacher item) { return repository.save(item); }
    public Teacher update(Long id, Teacher item) {
        item.setId(id);
        return repository.save(item);
    }
    public void delete(Long id) { repository.deleteById(id); }
}
