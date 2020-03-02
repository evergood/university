package com.foxminded.university.dao.impl;

import com.foxminded.university.domain.Role;
import com.foxminded.university.domain.Student;
import config.ConfigTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@JdbcTest
@ContextConfiguration(classes = ConfigTest.class)
public class StudentDaoTest {

    @Autowired
    @Qualifier("studentDao")
    StudentDaoImpl studentDao;

    @Test
    void studentDaoShouldInsertStudent() {
        String email = "123@gmail.com";
        Student expected = Student.builder()
                .email(email)
                .password("23gdfg")
                .firstName("Garry")
                .lastName("Cooper")
                .build();
        studentDao.create(expected);
        Student actual = studentDao.getByEmail(email).get();
        assertThat(expected, is(actual));
    }

    @Test
    void studentDaoShouldReturnStudentById() {
        Student actual = studentDao.getById(10).get();
        Student expected = Student.builder()
                .email("10@gmail.com")
                .password("123_456")
                .firstName("Emma")
                .lastName("Lee")
                .build();
        assertThat(expected, is(actual));
    }

    @Test
    void studentDaoShouldUpdateStudent() {
        String email = "1@gmail.com";
        Student expected = Student.builder()
                .firstName("Garry")
                .lastName("Cooper")
                .email(email)
                .password("23gdfg")
                .build();
        studentDao.update(expected);
        Student actual = studentDao.getByEmail(email).get();
        assertThat(expected, is(actual));
    }

    @Test
    void studentDaoShouldDeleteStudent() {
        Integer id = 2;
        studentDao.deleteById(id);
        boolean isExist = studentDao.isExist(id);
        assertThat(isExist, is(false));
    }

    @Test
    void studentDaoShouldReturnPageOfStudents() {
        int pageNum = 1;
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
        List<Student> actual = studentDao.getAllEntities(pageNum, elemsPerPage);
        assertThat(expected, is(actual));
    }
}
