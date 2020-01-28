package com.foxminded.university.service.impl;

import com.foxminded.university.dao.AbstractDao;
import com.foxminded.university.dao.RoomDao;
import com.foxminded.university.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {

    private RoomDao roomDao;

    @Autowired
    public RoomService(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public Optional<Room> getById(Integer id) {
        return roomDao.getById(id);
    }

    public boolean delete(Room room) {
        return roomDao.delete(room);
    }

    public boolean update(Room room) {
        return roomDao.update(room);
    }

    public boolean create(Room room) {
        return roomDao.create(room);
    }

    public boolean isExist(Room room) {
        return roomDao.isExist(room);
    }


}
