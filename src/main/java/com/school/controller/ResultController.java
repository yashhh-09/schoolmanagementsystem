package com.school.controller;

import com.school.entity.Result;
import com.school.service.ResultService;
import com.school.repository.ResultRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/results")
@CrossOrigin(origins = "*")
public class ResultController {
    private final ResultService service; private final ResultRepository repository;
    public ResultController(ResultService service, ResultRepository repository) { this.service=service; this.repository=repository; }
    @GetMapping public List<Result> findAll(){ return service.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Result> findById(@PathVariable Long id){ return ResponseEntity.ok(service.findById(id)); }
    @GetMapping("/student/{studentId}") public List<Result> byStudent(@PathVariable Long studentId){ return repository.findByStudentId(studentId); }
    @PostMapping public ResponseEntity<Result> create(@RequestBody Result item){ return ResponseEntity.ok(service.save(item)); }
    @PutMapping("/{id}") public ResponseEntity<Result> update(@PathVariable Long id,@RequestBody Result item){ return ResponseEntity.ok(service.update(id,item)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.noContent().build(); }
}
