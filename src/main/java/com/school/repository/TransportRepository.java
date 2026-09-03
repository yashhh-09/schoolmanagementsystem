package com.school.repository;

import com.school.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {

}
