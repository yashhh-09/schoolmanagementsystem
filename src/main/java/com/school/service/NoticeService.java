package com.school.service;

import com.school.entity.Notice;
import com.school.repository.NoticeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoticeService {
    private final NoticeRepository repository;
    public NoticeService(NoticeRepository repository) { this.repository = repository; }
    public List<Notice> findAll() { return repository.findAll(); }
    public Notice findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Notice not found: " + id));
    }
    public Notice save(Notice item) { return repository.save(item); }
    public Notice update(Long id, Notice item) {
        item.setId(id);
        return repository.save(item);
    }
    public void delete(Long id) { repository.deleteById(id); }
}
