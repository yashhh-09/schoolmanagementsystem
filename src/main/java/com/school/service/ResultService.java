package com.school.service;

import com.school.entity.Result;
import com.school.repository.ResultRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResultService {
    private final ResultRepository repository;
    public ResultService(ResultRepository repository) { this.repository = repository; }
    public List<Result> findAll() { return repository.findAll(); }
    public Result findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Result not found: " + id));
    }
    public Result save(Result item) { return repository.save(item); }
    public Result update(Long id, Result item) {
        item.setId(id);
        return repository.save(item);
    }
    public void delete(Long id) { repository.deleteById(id); }
}
