package com.school.repository;

import com.school.entity.LibraryBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface LibraryBookRepository extends JpaRepository<LibraryBook, Long> {

}
