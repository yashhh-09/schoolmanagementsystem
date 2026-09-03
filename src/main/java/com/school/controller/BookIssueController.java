package com.school.controller;

import com.school.entity.BookIssue;
import com.school.service.BookIssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/library/issues")
@CrossOrigin(origins = "*")
public class BookIssueController {
    private final BookIssueService service;
    public BookIssueController(BookIssueService service) { this.service = service; }

    @GetMapping
    public List<BookIssue> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<BookIssue> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<BookIssue> create(@RequestBody BookIssue item) {
        return ResponseEntity.ok(service.save(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookIssue> update(@PathVariable Long id, @RequestBody BookIssue item) {
        return ResponseEntity.ok(service.update(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
