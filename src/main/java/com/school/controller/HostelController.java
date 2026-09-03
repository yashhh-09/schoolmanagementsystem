package com.school.controller;

import com.school.entity.Hostel;
import com.school.service.HostelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hostel")
@CrossOrigin(origins = "*")
public class HostelController {
    private final HostelService service;
    public HostelController(HostelService service) { this.service = service; }

    @GetMapping
    public List<Hostel> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Hostel> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Hostel> create(@RequestBody Hostel item) {
        return ResponseEntity.ok(service.save(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hostel> update(@PathVariable Long id, @RequestBody Hostel item) {
        return ResponseEntity.ok(service.update(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
