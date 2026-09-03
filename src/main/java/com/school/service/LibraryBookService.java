package com.school.service;

import com.school.entity.LibraryBook;
import com.school.repository.LibraryBookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibraryBookService {
    private final LibraryBookRepository repository;
    public LibraryBookService(LibraryBookRepository repository) { this.repository = repository; }
    public List<LibraryBook> findAll() { return repository.findAll(); }
    public LibraryBook findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("LibraryBook not found: " + id));
    }
    public LibraryBook save(LibraryBook item) { return repository.save(item); }
    public LibraryBook update(Long id, LibraryBook item) {
        item.setId(id);
        return repository.save(item);
    }
    public void delete(Long id) { repository.deleteById(id); }
}
