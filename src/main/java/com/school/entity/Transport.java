package com.school.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transport")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Transport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vehicleNumber;
    private String routeName;
    private String driverName;
    private String driverPhone;
    private String pickupPoint;
}
