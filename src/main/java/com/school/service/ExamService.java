package com.school.service;

import com.school.entity.Exam;
import com.school.repository.ExamRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExamService {
    private final ExamRepository repository;
    public ExamService(ExamRepository repository) { this.repository = repository; }
    public List<Exam> findAll() { return repository.findAll(); }
    public Exam findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Exam not found: " + id));
    }
    public Exam save(Exam item) { return repository.save(item); }
    public Exam update(Long id, Exam item) {
        item.setId(id);
        return repository.save(item);
    }
    public void delete(Long id) { repository.deleteById(id); }
}
