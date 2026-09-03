package com.school.controller;

import com.school.entity.Transport;
import com.school.service.TransportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transport")
@CrossOrigin(origins = "*")
public class TransportController {
    private final TransportService service;
    public TransportController(TransportService service) { this.service = service; }

    @GetMapping
    public List<Transport> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Transport> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Transport> create(@RequestBody Transport item) {
        return ResponseEntity.ok(service.save(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transport> update(@PathVariable Long id, @RequestBody Transport item) {
        return ResponseEntity.ok(service.update(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
