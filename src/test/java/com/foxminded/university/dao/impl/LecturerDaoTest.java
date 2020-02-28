package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.LecturerDao;
import com.foxminded.university.domain.Lecturer;
import com.foxminded.university.domain.Role;
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
    LecturerDao lecturerDao;

    @Test
    public void lecturerDaoShouldInsertLecturer() {
        String email = "100@gmail.com";
        Lecturer expected = Lecturer.builder()
                .withEmail(email)
                .withPassword("325dg#%")
                .withRole(Role.LECTURER)
                .withFirstName("Garry")
                .withLastName("Cooper")
                .withRank("Professor")
                .build();
        lecturerDao.create(expected);
        Lecturer lecturer = lecturerDao.getByEmail(email).get();
        assertEquals(expected, lecturer);
    }

    @Test
    public void lecturerDaoShouldReturnLecturerById() {
        Lecturer lecturer = lecturerDao.getById(20).get();
        Lecturer expected = Lecturer.builder()
                .withEmail("20@gmail.com")
                .withPassword("123_456")
                .withFirstName("Emma")
                .withLastName("Lee")
                .withRank("Assistant Professor")
                .build();
        assertEquals(expected, lecturer);
    }

    @Test
    public void lecturerDaoShouldUpdateLecturer() {
        String email = "20@gmail.com";
        Lecturer expected = Lecturer.builder()
                .withEmail(email)
                .withPassword("325dg#%")
                .withFirstName("Garry")
                .withLastName("Cooper")
                .withRank("Assistant Professor")
                .build();
        lecturerDao.update(expected);
        Lecturer lecturer = lecturerDao.getByEmail(email).get();
        assertEquals(expected, lecturer);
    }

    @Test
    public void lecturerDaoShouldDeleteLecturer() {
        Integer id = 20;
        lecturerDao.deleteById(id);
        boolean isExist = lecturerDao.isExist(id);
        assertFalse(isExist);
    }
}
