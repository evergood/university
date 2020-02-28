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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
        Lecturer actual = lecturerDao.getByEmail(email).get();
        assertThat(expected, is(actual));
    }

    @Test
    public void lecturerDaoShouldReturnLecturerById() {
        Lecturer actual = lecturerDao.getById(20).get();
        Lecturer expected = Lecturer.builder()
                .withEmail("20@gmail.com")
                .withPassword("123_456")
                .withFirstName("Emma")
                .withLastName("Lee")
                .withRank("Assistant Professor")
                .build();
        assertThat(expected, is(actual));
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
        Lecturer actual = lecturerDao.getByEmail(email).get();
        assertThat(expected, is(actual));
    }

    @Test
    public void lecturerDaoShouldDeleteLecturer() {
        Integer id = 20;
        lecturerDao.deleteById(id);
        boolean isExist = lecturerDao.isExist(id);
        assertThat(isExist, is(false));
    }
}
