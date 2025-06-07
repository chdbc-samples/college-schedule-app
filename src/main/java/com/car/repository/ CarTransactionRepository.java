package com.car.repository;

import com.car.entity.CarTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarTransactionRepository extends JpaRepository<CarTransaction, Long> {
}
