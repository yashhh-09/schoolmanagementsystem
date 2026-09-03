package com.school.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book_issues")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BookIssue {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookId;
    private Long studentId;
    private String issueDate;
    private String returnDate;
    private String status;
}
