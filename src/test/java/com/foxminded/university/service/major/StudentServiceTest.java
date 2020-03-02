package com.foxminded.university.service.major;

import com.foxminded.university.dao.CourseDao;
import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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
        Student expected = Student.builder().build();
        when(studentDao.getById(id)).thenReturn(Optional.of(expected));

        Student actual = studentService.getById(id).get();

        assertThat(expected, is(actual));
    }

    @Test
    void studentServiceShouldDeleteStudentById() {
        Integer id = 10;
        when(studentDao.isExist(id)).thenReturn(false);

        boolean actual = studentService.isExist(id);

        assertThat(actual, is(false));
    }

    @Test
    void studentServiceShouldUpdateStudent() {
        Integer id = 10;
        Student expected = Student.builder().build();
        when(studentDao.getById(id)).thenReturn(Optional.of(expected));

        studentService.update(expected);
        Student actual = studentDao.getById(id).get();

        assertThat(expected, is(actual));
    }

    @Test
    void studentServiceShouldInsertStudent() {
        Integer id = 10;
        Student expected = Student.builder().build();
        when(studentDao.getById(id)).thenReturn(Optional.of(expected));

        studentService.create(expected);
        Student actual = studentDao.getById(id).get();

        assertThat(expected, is(actual));
    }

    @Test
    void studentServiceShouldViewMarks() {
        Integer id = 10;
        Map<String, Character> expected = new HashMap<>();
        expected.put("Biology", 'A');
        expected.put("Law", 'D');
        when(studentDao.viewMarks(id)).thenReturn(expected);

        Map<String, Character> actual = studentService.viewMarks(id);

        assertThat(expected, is(actual));
    }

    @Test
    void studentServiceShouldViewStudentSchedule() {
        Integer id = 10;
        Map<String, Integer> expected = new HashMap<>();
        expected.put("Biology", 1);
        expected.put("Law", 2);
        when(studentDao.getStudentSchedule(id)).thenReturn(expected);

        Map<String, Integer> actual = studentService.getStudentSchedule(id);

        assertThat(expected, is(actual));
    }

    @Test
    void studentServiceShouldGetPageOfStudents() {
        String pageNum = "1";
        int elemsPerPage = 5;
        List<Student> expected = new ArrayList<>();
        expected.add(Student.builder().firstName("Mason")
                .lastName("Sullivan").email("1@gmail.com").password("123_456")
                .build());
        expected.add(Student.builder().firstName("Harper")
                .lastName("Williams").email("2@gmail.com").password("123_456")
                .build());
        expected.add(Student.builder().firstName("James")
                .lastName("Gregory").email("3@gmail.com").password("123_456")
                .build());
        expected.add(Student.builder().firstName("Olivia")
                .lastName("Gregory").email("4@gmail.com").password("123_456")
                .build());
        expected.add(Student.builder().firstName("Olivia")
                .lastName("Smith").email("5@gmail.com").password("123_456")
                .build());
        when(studentDao.getAllEntities(Integer.parseInt(pageNum), elemsPerPage))
                .thenReturn(expected);

        List<Student> actual = studentService.getAllStudents(pageNum);

        assertThat(expected, is(actual));
    }

    @Test
    void studentServiceShouldGetFirstPageOfStudentsIfPAgeNumGreaterThanMaxPage() {
        String pageNum = "10";
        int elemsPerPage = 5;
        List<Student> expected = new ArrayList<>();
        expected.add(Student.builder().firstName("Mason")
                .lastName("Sullivan").email("1@gmail.com").password("123_456")
                .build());
        expected.add(Student.builder().firstName("Harper")
                .lastName("Williams").email("2@gmail.com").password("123_456")
                .build());
        expected.add(Student.builder().firstName("James")
                .lastName("Gregory").email("3@gmail.com").password("123_456")
                .build());
        expected.add(Student.builder().firstName("Olivia")
                .lastName("Gregory").email("4@gmail.com").password("123_456")
                .build());
        expected.add(Student.builder().firstName("Olivia")
                .lastName("Smith").email("5@gmail.com").password("123_456")
                .build());
        when(studentDao.getAllEntities(1, elemsPerPage)).thenReturn(expected);

        List<Student> actual = studentService.getAllStudents(pageNum);

        assertThat(expected, is(actual));
    }

    @Test
    void studentServiceShouldGetFirstPageOfStudentsIfPAgeNumLessThanZero() {
        String pageNum = "-10";
        int elemsPerPage = 5;
        List<Student> expected = new ArrayList<>();
        expected.add(Student.builder().firstName("Mason")
                .lastName("Sullivan").email("1@gmail.com").password("123_456")
                .build());
        expected.add(Student.builder().firstName("Harper")
                .lastName("Williams").email("2@gmail.com").password("123_456")
                .build());
        expected.add(Student.builder().firstName("James")
                .lastName("Gregory").email("3@gmail.com").password("123_456")
                .build());
        expected.add(Student.builder().firstName("Olivia")
                .lastName("Gregory").email("4@gmail.com").password("123_456")
                .build());
        expected.add(Student.builder().firstName("Olivia")
                .lastName("Smith").email("5@gmail.com").password("123_456")
                .build());
        when(studentDao.getAllEntities(1, elemsPerPage)).thenReturn(expected);

        List<Student> actual = studentService.getAllStudents(pageNum);

        assertThat(expected, is(actual));
    }
}
