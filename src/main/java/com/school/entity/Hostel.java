package com.school.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hostels")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Hostel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hostelName;
    private String roomNumber;
    private Integer capacity;
    private Integer occupied;
    private Long studentId;
}
