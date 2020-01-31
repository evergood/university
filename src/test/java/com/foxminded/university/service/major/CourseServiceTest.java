package com.foxminded.university.service.major;

import com.foxminded.university.dao.CourseDao;
import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Student;
import com.foxminded.university.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseDao courseDao;
    @InjectMocks
    private CourseService courseService;

    @Test
    void courseServiceShouldReturnCourseById() {
        Integer id = 10;
        Course expected = new Course(id, "Biology");
        when(courseDao.getById(id)).thenReturn(Optional.of(expected));

        Course actual = courseService.getById(id).get();

        assertEquals(expected, actual);
    }

    @Test
    void courseDaoShouldDeleteCourseById() {
        Integer id = 10;
        when(courseDao.isExist(id)).thenReturn(false);

        boolean actual = courseService.isExist(id);

        assertFalse(actual);
    }

    @Test
    void courseServiceShouldUpdateCourse() {
        Integer id = 10;
        Course expected = new Course(id, "Biology");
        when(courseDao.getById(id)).thenReturn(Optional.of(expected));

        courseService.update(expected);
        Course actual = courseService.getById(id).get();

        assertEquals(expected, actual);
    }

    @Test
    void courseServiceShouldCreateCourse() {
        Integer id = 10;
        Course expected = new Course(id, "Biology");
        when(courseDao.getById(id)).thenReturn(Optional.of(expected));

        courseService.create(expected);
        Course actual = courseService.getById(id).get();

        assertEquals(expected, actual);
    }

    @Test
    void courseServiceShouldFindCoursesByStudentId() {
        Integer id = 10;
        List<Course> expected = Arrays.asList(new Course(1, "Math"), new Course(2, "Law"));
        when(courseDao.findCoursesByStudentId(id)).thenReturn(expected);

        List<Course> actual = courseService.findCoursesByStudentId(id);

        assertEquals(expected, actual);
    }

    @Test
    void courseServiceShouldFindCStudentsByCourseId() {
        Integer id = 10;
        List<User> expected = Arrays.asList(Student.builder().withId(1).build(),
                Student.builder().withId(2).build());
        when(courseDao.findStudentsByCourseId(id)).thenReturn(expected);

        List<User> actual = courseService.findStudentsByCourseId(id);

        assertEquals(expected, actual);
    }
}
