package com.school.repository;

import com.school.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
