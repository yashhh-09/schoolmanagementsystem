package com.school.controller;

import com.school.entity.Fee;
import com.school.service.FeeService;
import com.school.repository.FeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fees")
@CrossOrigin(origins = "*")
public class FeeController {
    private final FeeService service; private final FeeRepository repository;
    public FeeController(FeeService service, FeeRepository repository) { this.service=service; this.repository=repository; }
    @GetMapping public List<Fee> findAll(){ return service.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Fee> findById(@PathVariable Long id){ return ResponseEntity.ok(service.findById(id)); }
    @GetMapping("/student/{studentId}") public List<Fee> byStudent(@PathVariable Long studentId){ return repository.findByStudentId(studentId); }
    @PostMapping public ResponseEntity<Fee> create(@RequestBody Fee item){ return ResponseEntity.ok(service.save(item)); }
    @PutMapping("/{id}") public ResponseEntity<Fee> update(@PathVariable Long id,@RequestBody Fee item){ return ResponseEntity.ok(service.update(id,item)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.noContent().build(); }
}
