package com.college.service;

import com.college.entity.Course;
import com.college.entity.Enrollment;
import com.college.entity.Student;
import com.college.repository.EnrollmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    @InjectMocks
    private EnrollmentService enrollmentService;

    private Enrollment enrollment;
    private Student student;
    private Course course;

    @BeforeEach
    void setUp() {
        // Ініціалізація entityManager за допомогою ReflectionTestUtils
        ReflectionTestUtils.setField(enrollmentService, "entityManager", entityManager);
        
        student = new Student();
        student.setStudentId(1L);
        student.setFirstName("Test");
        student.setLastName("Student");

        course = new Course();
        course.setCourseId(1L);
        course.setCourseName("Test Course");
        course.setCredits(3);

        enrollment = new Enrollment();
        enrollment.setEnrollmentId(1L);
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now());
    }

    @Test
    void findAll_ShouldReturnAllEnrollments() {
        when(enrollmentRepository.findAll()).thenReturn(Arrays.asList(enrollment));

        List<Enrollment> result = enrollmentService.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getEnrollmentId()).isEqualTo(1L);
        verify(enrollmentRepository).findAll();
    }

    @Test
    void findById_WhenExists_ShouldReturnEnrollment() {
        when(enrollmentRepository.findById(1L)).thenReturn(Optional.of(enrollment));

        Optional<Enrollment> result = enrollmentService.findById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getEnrollmentId()).isEqualTo(1L);
        verify(enrollmentRepository).findById(1L);
    }

    @Test
    void save_ShouldReturnSavedEnrollment() {
        when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(enrollment);

        Enrollment result = enrollmentService.save(enrollment);

        assertThat(result).isNotNull();
        assertThat(result.getEnrollmentId()).isEqualTo(1L);
        assertThat(result.getStudent()).isEqualTo(student);
        assertThat(result.getCourse()).isEqualTo(course);
        verify(enrollmentRepository).save(enrollment);
    }

    @Test
    void enroll_ShouldSaveEnrollment() {
        when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(enrollment);

        Enrollment result = enrollmentService.enroll(enrollment);

        assertThat(result).isNotNull();
        assertThat(result.getEnrollmentId()).isEqualTo(1L);
        assertThat(result.getEnrollmentDate()).isNotNull();
        verify(enrollmentRepository).save(enrollment);
    }

    @Test
    void deleteById_ShouldCallRepository() {
        doNothing().when(enrollmentRepository).deleteById(1L);

        enrollmentService.deleteById(1L);

        verify(enrollmentRepository).deleteById(1L);
    }

    @Test
    void findByStudentNameAndCourseName_ShouldReturnEnrollments() {
        // Підготовка тестових даних
        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.setParameter(anyString(), any())).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(enrollment));

        // Виклик методу та перевірка результатів
        List<Enrollment> result = enrollmentService.findByStudentNameAndCourseName("Test Student", "Test Course");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getStudent().getFirstName()).isEqualTo("Test");
        assertThat(result.get(0).getStudent().getLastName()).isEqualTo("Student");
        assertThat(result.get(0).getCourse().getCourseName()).isEqualTo("Test Course");
        
        verify(entityManager).createQuery(anyString());
        verify(query, times(3)).setParameter(anyString(), any());
        verify(query).getResultList();
    }

    @Test
    void deleteByStudentNameAndCourseName_ShouldDeleteEnrollments() {
        // Підготовка тестових даних
        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.setParameter(anyString(), any())).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(enrollment));

        // Виклик методу
        enrollmentService.deleteByStudentNameAndCourseName("Test Student", "Test Course");

        // Перевірка, що видалення було викликано
        verify(enrollmentRepository).delete(enrollment);
    }
}