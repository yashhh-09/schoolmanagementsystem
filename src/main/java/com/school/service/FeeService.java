package com.school.service;

import com.school.entity.Fee;
import com.school.repository.FeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeeService {
    private final FeeRepository repository;
    public FeeService(FeeRepository repository) { this.repository = repository; }
    public List<Fee> findAll() { return repository.findAll(); }
    public Fee findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Fee not found: " + id));
    }
    public Fee save(Fee item) { return repository.save(item); }
    public Fee update(Long id, Fee item) {
        item.setId(id);
        return repository.save(item);
    }
    public void delete(Long id) { repository.deleteById(id); }
}
