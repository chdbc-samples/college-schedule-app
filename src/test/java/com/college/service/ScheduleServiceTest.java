package com.college.service;

import com.college.entity.ScheduleInfoDTO;
import com.college.repository.ClassScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private ClassScheduleRepository classScheduleRepository;

    @Mock
    private Query query;

    private ScheduleService scheduleService;

    private ScheduleInfoDTO scheduleInfoDTO;

    @BeforeEach
    void setUp() {
        scheduleService = new ScheduleService(classScheduleRepository);
        ReflectionTestUtils.setField(scheduleService, "entityManager", entityManager);
        
        scheduleInfoDTO = new ScheduleInfoDTO(
            "Test", "Student",
            "Test", "Teacher",
            "Test Course",
            "Test Department",
            "101",
            30,
            "Fall",
            2024,
            LocalTime.of(9, 0),
            LocalTime.of(10, 30)
        );
    }

    @Test
    void getScheduleInfo_ShouldReturnScheduleInfoList() {
        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(scheduleInfoDTO));

        List<ScheduleInfoDTO> result = scheduleService.getScheduleInfo();

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(1);
        
        ScheduleInfoDTO dto = result.get(0);
        assertThat(dto.getStudentFirstName()).isEqualTo("Test");
        assertThat(dto.getStudentLastName()).isEqualTo("Student");
        assertThat(dto.getTeacherFirstName()).isEqualTo("Test");
        assertThat(dto.getTeacherLastName()).isEqualTo("Teacher");
        assertThat(dto.getCourseName()).isEqualTo("Test Course");
        assertThat(dto.getDepartmentName()).isEqualTo("Test Department");
        assertThat(dto.getRoomNumber()).isEqualTo("101");
        assertThat(dto.getRoomCapacity()).isEqualTo(30);
        assertThat(dto.getSemester()).isEqualTo("Fall");
        assertThat(dto.getYear()).isEqualTo(2024);
        assertThat(dto.getStartTime()).isEqualTo(LocalTime.of(9, 0));
        assertThat(dto.getEndTime()).isEqualTo(LocalTime.of(10, 30));

        verify(entityManager).createQuery(anyString());
        verify(query).getResultList();
    }

    @Test
    void getScheduleInfo_WhenNoResults_ShouldReturnEmptyList() {
        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList());

        List<ScheduleInfoDTO> result = scheduleService.getScheduleInfo();

        assertThat(result).isEmpty();
        verify(entityManager).createQuery(anyString());
        verify(query).getResultList();
    }
}