package com.school.service;

import com.school.entity.Hostel;
import com.school.repository.HostelRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HostelService {
    private final HostelRepository repository;
    public HostelService(HostelRepository repository) { this.repository = repository; }
    public List<Hostel> findAll() { return repository.findAll(); }
    public Hostel findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Hostel not found: " + id));
    }
    public Hostel save(Hostel item) { return repository.save(item); }
    public Hostel update(Long id, Hostel item) {
        item.setId(id);
        return repository.save(item);
    }
    public void delete(Long id) { repository.deleteById(id); }
}
