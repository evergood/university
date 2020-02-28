package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.CrudDao;
import com.foxminded.university.domain.WeeklyTimeUnit;
import config.ConfigTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@JdbcTest
@ContextConfiguration(classes = ConfigTest.class)
public class WeeklyTimeUnitDaoTest {

    @Autowired
    @Qualifier("weeklyTimeUnitDao")
    CrudDao<WeeklyTimeUnit> weeklyTimeUnitDao;

    @Test
    void weeklyTimeUnitDaoShouldInsertWeeklyTimeUnit() {
        WeeklyTimeUnit expected = WeeklyTimeUnit.builder()
                .withId(20)
                .withStartTime(LocalTime.of(10, 15))
                .withEndTime(LocalTime.of(12, 0))
                .withDayOfWeek(DayOfWeek.MONDAY)
                .build();
        weeklyTimeUnitDao.create(expected);
        WeeklyTimeUnit actual = weeklyTimeUnitDao.getById(20).get();
        assertThat(expected, is(actual));
    }

    @Test
    void weeklyTimeUnitDaoShouldReturnWeeklyTimeUnitById() {
        WeeklyTimeUnit actual = weeklyTimeUnitDao.getById(10).get();
        WeeklyTimeUnit expected = WeeklyTimeUnit.builder()
                .withStartTime(LocalTime.of(10, 0))
                .withEndTime(LocalTime.of(12, 0))
                .withDayOfWeek(DayOfWeek.THURSDAY)
                .withId(10)
                .build();
        assertThat(expected, is(actual));
    }

    @Test
    void weeklyTimeUnitDaoShouldUpdateWeeklyTimeUnit() {
        WeeklyTimeUnit expected = WeeklyTimeUnit.builder()
                .withId(15)
                .withStartTime(LocalTime.of(10, 15))
                .withEndTime(LocalTime.of(12, 0))
                .withDayOfWeek(DayOfWeek.MONDAY)
                .build();
        weeklyTimeUnitDao.update(expected);
        WeeklyTimeUnit actual = weeklyTimeUnitDao.getById(15).get();
        assertThat(expected, is(actual));
    }

    @Test
    void weeklyTimeUnitDaoShouldDeleteWeeklyTimeUnit() {
        Integer id = 10;
        weeklyTimeUnitDao.deleteById(id);
        boolean isExist = weeklyTimeUnitDao.isExist(id);
        assertThat(isExist, is(false));
    }
}
