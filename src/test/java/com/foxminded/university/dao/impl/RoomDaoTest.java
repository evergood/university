package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.CrudDao;
import com.foxminded.university.domain.Room;
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
public class RoomDaoTest {

    @Autowired
    @Qualifier("roomDao")
    CrudDao<Room> roomDao;

    @Test
    void roomDaoShouldInsertRoom() {
        Room expected = new Room(666);
        roomDao.create(expected);
        Room actual = roomDao.getById(666).get();
        assertThat(expected, is(actual));
    }

    @Test
    void roomDaoShouldReturnRoomById() {
        Room actual = roomDao.getById(101).get();
        Room expected = new Room(101);
        assertThat(expected, is(actual));
    }

    @Test
    void roomDaoShouldUpdateRoom() {
        Room expected = new Room(666);
        roomDao.update(expected);
        Room actual = roomDao.getById(666).get();
        assertThat(expected, is(actual));
    }

    @Test
    void roomDaoShouldDeleteRoom() {
        Integer id = 666;
        roomDao.deleteById(id);
        boolean isExist = roomDao.isExist(id);
        assertThat(isExist, is(false));
    }
}
