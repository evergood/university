package com.foxminded.university.service.impl;

import com.foxminded.university.dao.AbstractDao;
import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Room;
import com.foxminded.university.service.DaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomServiceImpl implements DaoService<Room, Integer> {

    AbstractDao<Room, Integer> roomDao;

    @Autowired
    public RoomServiceImpl(AbstractDao<Room, Integer> roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public Optional<Room> getById(Integer id) {
        return roomDao.getById(id);
    }

    @Override
    public boolean delete(Room room) {
        return roomDao.delete(room);
    }

    @Override
    public boolean update(Room room) {
        return roomDao.update(room);
    }

    @Override
    public boolean create(Room room) {
        return roomDao.create(room);
    }

    @Override
    public boolean isExist(Room room) {
        return roomDao.isExist(room);
    }


}
