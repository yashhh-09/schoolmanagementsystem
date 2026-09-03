package com.school.controller;

import com.school.entity.Attendance;
import com.school.service.AttendanceService;
import com.school.repository.AttendanceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {
    private final AttendanceService service;
    private final AttendanceRepository repository;
    public AttendanceController(AttendanceService service, AttendanceRepository repository) {
        this.service = service; this.repository = repository;
    }
    @GetMapping public List<Attendance> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Attendance> findById(@PathVariable Long id) { return ResponseEntity.ok(service.findById(id)); }
    @GetMapping("/student/{studentId}") public List<Attendance> byStudent(@PathVariable Long studentId) { return repository.findByStudentId(studentId); }
    @PostMapping public ResponseEntity<Attendance> create(@RequestBody Attendance item) { return ResponseEntity.ok(service.save(item)); }
    @PutMapping("/{id}") public ResponseEntity<Attendance> update(@PathVariable Long id, @RequestBody Attendance item) { return ResponseEntity.ok(service.update(id,item)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }
}
