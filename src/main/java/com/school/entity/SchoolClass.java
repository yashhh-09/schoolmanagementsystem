package com.school.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "school_classes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SchoolClass {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String className;
    private String section;
    private String academicYear;
    private Long classTeacherId;
}
