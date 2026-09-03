package com.school.service;

import com.school.entity.Subject;
import com.school.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository repository;
    public SubjectService(SubjectRepository repository) { this.repository = repository; }
    public List<Subject> findAll() { return repository.findAll(); }
    public Subject findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Subject not found: " + id));
    }
    public Subject save(Subject item) { return repository.save(item); }
    public Subject update(Long id, Subject item) {
        item.setId(id);
        return repository.save(item);
    }
    public void delete(Long id) { repository.deleteById(id); }
}
