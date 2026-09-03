package com.school.controller;

import com.school.entity.SchoolClass;
import com.school.service.SchoolClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "*")
public class SchoolClassController {
    private final SchoolClassService service;
    public SchoolClassController(SchoolClassService service) { this.service = service; }

    @GetMapping
    public List<SchoolClass> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolClass> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SchoolClass> create(@RequestBody SchoolClass item) {
        return ResponseEntity.ok(service.save(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolClass> update(@PathVariable Long id, @RequestBody SchoolClass item) {
        return ResponseEntity.ok(service.update(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
