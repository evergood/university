package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.CrudDao;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@JdbcTest
@ContextConfiguration(classes = ConfigTest.class)
public class StudentDaoTest {

    @Autowired
    @Qualifier("studentDao")
    StudentDaoImpl studentDao;

    @Test
    void studentDaoShouldInsertStudent() {
        Student expected = Student.builder()
                .withId(30)
                .withEmail("123@gmail.com")
                .withPassword("23gdfg")
                .withRole(Role.STUDENT)
                .withFirstName("Garry")
                .withLastName("Cooper")
                .build();
        studentDao.create(expected);
        Student student = studentDao.getById(30).get();
        assertEquals(expected, student);
    }

    @Test
    void studentDaoShouldReturnStudentById() {
        Student student = studentDao.getById(10).get();
        Student expected = Student.builder()
                .withFirstName("Emma")
                .withLastName("Lee")
                .withId(10)
                .build();
        assertEquals(expected, student);
    }

    @Test
    void studentDaoShouldUpdateStudent() {
        Student expected = Student.builder()
                .withFirstName("Garry")
                .withLastName("Cooper")
                .withEmail("123@gmail.com")
                .withPassword("23gdfg")
                .withId(5)
                .build();
        studentDao.update(expected);
        Student student = studentDao.getById(5).get();
        assertEquals(expected, student);
    }

    @Test
    void studentDaoShouldDeleteStudent() {
        Integer id = 2;
        studentDao.deleteById(id);
        boolean isExist = studentDao.isExist(id);
        assertFalse(isExist);
    }

    @Test
    void studentDaoShouldReturnPageOfStudents() {
        int pageNum = 1;
        int elemsPerPage = 5;
        List<Student> expected = new ArrayList<>();
        expected.add(Student.builder().withId(1).withFirstName("Mason")
                .withLastName("Sullivan").withEmail("1@gmail.com").withPassword("123_456")
                .build());
        expected.add(Student.builder().withId(2).withFirstName("Harper")
                .withLastName("Williams").withEmail("2@gmail.com").withPassword("123_456")
                .build());
        expected.add(Student.builder().withId(3).withFirstName("James")
                .withLastName("Gregory").withEmail("3@gmail.com").withPassword("123_456")
                .build());
        expected.add(Student.builder().withId(4).withFirstName("Olivia")
                .withLastName("Gregory").withEmail("4@gmail.com").withPassword("123_456")
                .build());
        expected.add(Student.builder().withId(5).withFirstName("Olivia")
                .withLastName("Smith").withEmail("5@gmail.com").withPassword("123_456")
                .build());
        List<Student> actual = studentDao.getAllStudents(pageNum, elemsPerPage);
        assertEquals(expected, actual);
    }
}
