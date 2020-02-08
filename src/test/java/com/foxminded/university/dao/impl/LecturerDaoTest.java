package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.CrudDao;
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
    CrudDao<Lecturer> lecturerDao;

    @Test
    public void lecturerDaoShouldInsertLecturer() {
        Lecturer expected = Lecturer.builder()
                .withEmail("20@gmail.com")
                .withPassword("325dg#%")
                .withRole(Role.LECTURER)
                .withId(30)
                .withFirstName("Garry")
                .withLastName("Cooper")
                .withRank("Professor")
                .build();
        lecturerDao.create(expected);
        Lecturer lecturer = lecturerDao.getById(30).get();
        assertEquals(expected, lecturer);
    }

    @Test
    public void lecturerDaoShouldReturnLecturerById() {
        Lecturer lecturer = lecturerDao.getById(20).get();
        Lecturer expected = Lecturer.builder()
                .withFirstName("Emma")
                .withLastName("Lee")
                .withId(20)
                .withRank("Assistant Professor")
                .build();
        assertEquals(expected, lecturer);
    }

    @Test
    public void lecturerDaoShouldUpdateLecturer() {
        Lecturer expected = Lecturer.builder()
                .withEmail("20@gmail.com")
                .withPassword("325dg#%")
                .withFirstName("Garry")
                .withLastName("Cooper")
                .withId(20)
                .withRank("Assistant Professor")
                .build();
        lecturerDao.update(expected);
        Lecturer lecturer = lecturerDao.getById(20).get();
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
