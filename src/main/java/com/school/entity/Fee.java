package com.school.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fees")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Fee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long studentId;
    private Double amount;
    private Double paidAmount;
    private Double dueAmount;
    private String paymentStatus;
    private String paymentDate;
    private String paymentMethod;
}
