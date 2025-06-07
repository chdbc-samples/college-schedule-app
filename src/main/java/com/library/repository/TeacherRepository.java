package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}