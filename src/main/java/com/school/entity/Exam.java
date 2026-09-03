package com.school.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exams")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Exam {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String examDate;
    private String className;
    private String academicYear;
}
