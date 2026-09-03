package com.school.repository;

import com.school.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

}
