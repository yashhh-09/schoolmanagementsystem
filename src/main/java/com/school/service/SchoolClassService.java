package com.school.service;

import com.school.entity.SchoolClass;
import com.school.repository.SchoolClassRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SchoolClassService {
    private final SchoolClassRepository repository;
    public SchoolClassService(SchoolClassRepository repository) { this.repository = repository; }
    public List<SchoolClass> findAll() { return repository.findAll(); }
    public SchoolClass findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("SchoolClass not found: " + id));
    }
    public SchoolClass save(SchoolClass item) { return repository.save(item); }
    public SchoolClass update(Long id, SchoolClass item) {
        item.setId(id);
        return repository.save(item);
    }
    public void delete(Long id) { repository.deleteById(id); }
}
