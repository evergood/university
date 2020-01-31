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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@JdbcTest
@ContextConfiguration(classes = ConfigTest.class)
public class StudentDaoTest {

    @Autowired
    @Qualifier("studentDao")
    CrudDao<Student> studentDao;

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
}
