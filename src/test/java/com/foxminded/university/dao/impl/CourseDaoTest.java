package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.CrudDao;
import com.foxminded.university.domain.Course;
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
public class CourseDaoTest {

    @Autowired
    @Qualifier("courseDao")
    CrudDao<Course> courseDao;

    @Test
    public void courseDaoShouldInsertCourse() {
        Course expected = new Course(11, "Rocket Science");
        courseDao.create(expected);
        Course actual = courseDao.getById(11).get();
        assertThat(expected, is(actual));
    }

    @Test
    public void courseDaoShouldReturnCourseById() {
        Course actual = courseDao.getById(7).get();
        Course expected = new Course(7, "Biology");
        assertThat(expected, is(actual));
    }

    @Test
    public void courseDaoShouldUpdateCourse() {
        Course expected = new Course(7, "Rocket Science");
        courseDao.update(expected);
        Course actual = courseDao.getById(7).get();
        assertThat(expected, is(actual));
    }

    @Test
    public void courseDaoShouldDeleteCourse() {
        Integer id = 7;
        courseDao.deleteById(id);
        boolean isExist = courseDao.isExist(id);
        assertThat(isExist, is(false));
    }
}
