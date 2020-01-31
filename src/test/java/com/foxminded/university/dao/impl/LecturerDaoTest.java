package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.CrudDao;
import com.foxminded.university.domain.Lecturer;
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
public class LecturerDaoTest {

    @Autowired
    @Qualifier("lecturerDao")
    CrudDao<Lecturer, Integer> lecturerDao;

    @Test
    void lecturerDaoShouldInsertLecturer() {
        Lecturer expected = Lecturer.builder()
                .withId(11)
                .withFirstName("Garry")
                .withLastName("Cooper")
                .build();
        lecturerDao.create(expected);
        Lecturer lecturer = lecturerDao.getById(11).get();
        assertEquals(expected, lecturer);
    }

    @Test
    void lecturerDaoShouldReturnLecturerById() {
        Lecturer lecturer = lecturerDao.getById(10).get();
        Lecturer expected = Lecturer.builder()
                .withFirstName("Emma")
                .withLastName("Lee")
                .withId(10)
                .build();
        assertEquals(expected, lecturer);
    }

    @Test
    void lecturerDaoShouldUpdateLecturer() {
        Lecturer expected = Lecturer.builder()
                .withFirstName("Garry")
                .withLastName("Cooper")
                .withId(5)
                .build();
        lecturerDao.update(expected);
        Lecturer lecturer = lecturerDao.getById(5).get();
        assertEquals(expected, lecturer);
    }

    @Test
    void lecturerDaoShouldDeleteLecturer() {
        Lecturer lecturer = Lecturer.builder().withId(2).build();
        lecturerDao.deleteById(lecturer);
        boolean isExist = lecturerDao.isExist(lecturer);
        assertFalse(isExist);
    }
}
