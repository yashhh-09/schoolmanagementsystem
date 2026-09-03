package com.school.service;

import com.school.entity.Transport;
import com.school.repository.TransportRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransportService {
    private final TransportRepository repository;
    public TransportService(TransportRepository repository) { this.repository = repository; }
    public List<Transport> findAll() { return repository.findAll(); }
    public Transport findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Transport not found: " + id));
    }
    public Transport save(Transport item) { return repository.save(item); }
    public Transport update(Long id, Transport item) {
        item.setId(id);
        return repository.save(item);
    }
    public void delete(Long id) { repository.deleteById(id); }
}
