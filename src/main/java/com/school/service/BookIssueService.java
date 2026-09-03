package com.school.service;

import com.school.entity.BookIssue;
import com.school.repository.BookIssueRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookIssueService {
    private final BookIssueRepository repository;
    public BookIssueService(BookIssueRepository repository) { this.repository = repository; }
    public List<BookIssue> findAll() { return repository.findAll(); }
    public BookIssue findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("BookIssue not found: " + id));
    }
    public BookIssue save(BookIssue item) { return repository.save(item); }
    public BookIssue update(Long id, BookIssue item) {
        item.setId(id);
        return repository.save(item);
    }
    public void delete(Long id) { repository.deleteById(id); }
}
