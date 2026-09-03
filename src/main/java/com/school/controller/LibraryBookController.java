package com.school.controller;

import com.school.entity.LibraryBook;
import com.school.service.LibraryBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/library/books")
@CrossOrigin(origins = "*")
public class LibraryBookController {
    private final LibraryBookService service;
    public LibraryBookController(LibraryBookService service) { this.service = service; }

    @GetMapping
    public List<LibraryBook> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryBook> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<LibraryBook> create(@RequestBody LibraryBook item) {
        return ResponseEntity.ok(service.save(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibraryBook> update(@PathVariable Long id, @RequestBody LibraryBook item) {
        return ResponseEntity.ok(service.update(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
