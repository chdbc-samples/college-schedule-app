package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}