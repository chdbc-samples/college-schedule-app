package com.car.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "car_transactions")
public class CarTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String buyerFullName;

    private String passportNumber;

    private String carBrand;

    private String carModel;

    private String carType;

    private Integer year;

    private Integer price;

    private String operationType;

    private Boolean insurance;

    private String insuranceCompanyAndPrice;

    private String managerName;

    private LocalDate saleDate;

    private LocalDateTime testDriveDateTime;

    // Getters and Setters
}
