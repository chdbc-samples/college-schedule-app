package com.college.service;

import com.college.entity.ScheduleInfoDTO;
import com.college.repository.ClassScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ScheduleService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ScheduleService(ClassScheduleRepository classScheduleRepository) {
    }

    @SuppressWarnings("unchecked")
    public List<ScheduleInfoDTO> getScheduleInfo() {
        String jpql = "SELECT new com.college.entity.ScheduleInfoDTO(" +
                "s.firstName, s.lastName, " +
                "t.firstName, t.lastName, " +
                "c.courseName, d.name, " +
                "r.roomNumber, r.capacity, " +
                "cs.semester, cs.year, " +
                "cs.startTime, cs.endTime) " +
                "FROM Enrollment e " +
                "JOIN e.student s " +
                "JOIN e.course c " +
                "JOIN c.classSchedules cs " +
                "JOIN cs.teacher t " +
                "JOIN c.department d " +
                "JOIN cs.room r";
            
        return entityManager.createQuery(jpql).getResultList();
    }
}