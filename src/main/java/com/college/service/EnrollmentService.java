package com.college.service;

import com.college.entity.Enrollment;
import com.college.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    public Optional<Enrollment> findById(Long id) {
        return enrollmentRepository.findById(id);
    }

    @Transactional
    public Enrollment save(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Transactional
    public void deleteById(Long id) {
        enrollmentRepository.deleteById(id);
    }

    @Transactional
    public Enrollment enroll(Enrollment enrollment) {
        // Additional business logic can be added here
        // such as checking course capacity, prerequisites, etc.
        return enrollmentRepository.save(enrollment);
    }
}