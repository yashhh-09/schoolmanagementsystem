package com.school.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "library_books")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LibraryBook {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String bookCode;
    private String title;
    private String author;
    private String category;
    private Integer quantity;
    private Integer availableQuantity;
}
