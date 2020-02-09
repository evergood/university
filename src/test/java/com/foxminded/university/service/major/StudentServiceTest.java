package com.foxminded.university.service.major;

import com.foxminded.university.dao.CourseDao;
import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentDao studentDao;
    @Mock
    private CourseDao courseDao;
    @InjectMocks
    private StudentService studentService;

    @Test
    void studentServiceShouldReturnStudentById() {
        Integer id = 10;
        Student expected = Student.builder().withId(1).build();
        when(studentDao.getById(id)).thenReturn(Optional.of(expected));

        Student actual = studentService.getById(id).get();

        assertEquals(expected, actual);
    }

    @Test
    void studentServiceShouldDeleteStudentById() {
        Integer id = 10;
        when(studentDao.isExist(id)).thenReturn(false);

        boolean actual = studentService.isExist(id);

        assertFalse(actual);
    }

    @Test
    void studentServiceShouldUpdateStudent() {
        Integer id = 10;
        Student expected = Student.builder().withId(1).build();
        when(studentDao.getById(id)).thenReturn(Optional.of(expected));

        studentService.update(expected);
        Student actual = studentDao.getById(id).get();

        assertEquals(expected, actual);
    }

    @Test
    void studentServiceShouldInsertStudent() {
        Integer id = 10;
        Student  expected = Student.builder().withId(1).build();
        when(studentDao.getById(id)).thenReturn(Optional.of(expected));

        studentService.create(expected);
        Student actual = studentDao.getById(id).get();

        assertEquals(expected, actual);
    }

    @Test
    void studentServiceShouldViewMarks() {
        Integer id = 10;
        Map<String, Character> expected = new HashMap<>();
        expected.put("Biology", 'A');
        expected.put("Law", 'D');
        when(studentDao.viewMarks(id)).thenReturn(expected);

        Map<String, Character> actual = studentService.viewMarks(id);

        assertEquals(expected, actual);
    }

    @Test
    void studentServiceShouldViewStudentSchedule() {
        Integer id = 10;
        Map<String, Integer> expected = new HashMap<>();
        expected.put("Biology", 1);
        expected.put("Law", 2);
        when(studentDao.getStudentSchedule(id)).thenReturn(expected);

        Map<String, Integer> actual = studentService.getStudentSchedule(id);

        assertEquals(expected, actual);
    }
}