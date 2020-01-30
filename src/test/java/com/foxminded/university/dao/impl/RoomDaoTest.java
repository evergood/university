package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.CrudDao;
import com.foxminded.university.domain.Room;
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
public class RoomDaoTest {

    @Autowired
    @Qualifier("roomDao")
    CrudDao<Room> roomDao;

    @Test
    void roomDaoShouldInsertRoom() {
        Room expected = new Room(666);
        roomDao.create(expected);
        Room room = roomDao.getById(666).get();
        assertEquals(expected, room);
    }

    @Test
    void roomDaoShouldReturnRoomById() {
        Room room = roomDao.getById(101).get();
        Room expected = new Room(101);
        assertEquals(expected, room);
    }

    @Test
    void roomDaoShouldUpdateRoom() {
        Room expected = new Room(666);
        roomDao.update(expected);
        Room room = roomDao.getById(666).get();
        assertEquals(expected, room);
    }

    @Test
    void roomDaoShouldDeleteRoom() {
        Room room = new Room(666);
        roomDao.deleteById(room);
        boolean isExist = roomDao.isExist(room);
        assertFalse(isExist);
    }
}
