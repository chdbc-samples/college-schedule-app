package com.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.car.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}