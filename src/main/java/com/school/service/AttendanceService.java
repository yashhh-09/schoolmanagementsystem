package com.school.service;

import com.school.entity.Attendance;
import com.school.repository.AttendanceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository repository;
    public AttendanceService(AttendanceRepository repository) { this.repository = repository; }
    public List<Attendance> findAll() { return repository.findAll(); }
    public Attendance findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Attendance not found: " + id));
    }
    public Attendance save(Attendance item) { return repository.save(item); }
    public Attendance update(Long id, Attendance item) {
        item.setId(id);
        return repository.save(item);
    }
    public void delete(Long id) { repository.deleteById(id); }
}
