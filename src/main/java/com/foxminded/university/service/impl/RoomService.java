package com.foxminded.university.service.impl;

import com.foxminded.university.dao.AbstractDao;
import com.foxminded.university.domain.Room;
import com.foxminded.university.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService extends AbstractService<Room> {

    @Autowired
    public RoomService(AbstractDao<Room> dao) {
        super(dao);
    }
}
