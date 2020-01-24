package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.AbstractDao;
import com.foxminded.university.dao.RoomDao;
import com.foxminded.university.domain.Room;
import com.foxminded.university.domain.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository("roomDao")
public class RoomDaoImpl extends AbstractDao<Room, Integer> implements RoomDao {

    private static final String SQL_FIND_ROOM = "SELECT * FROM rooms WHERE room_id = ?";
    private static final String SQL_DELETE_ROOM = "DELETE FROM rooms WHERE room_id = ?";
    private static final String SQL_UPDATE_ROOM = "INSERT INTO rooms(room_id) VALUES(?)";
    private static final String SQL_INSERT_ROOM = "INSERT INTO rooms(room_id) VALUES(?)";
    private static final String SQL_ROOM_EXISTS = "SELECT EXISTS(SELECT FROM rooms WHERE room_id = ?)";

    @Autowired
    protected RoomDaoImpl(DataSource dataSource) {
        super(SQL_FIND_ROOM, SQL_DELETE_ROOM, SQL_UPDATE_ROOM,
                SQL_INSERT_ROOM, SQL_ROOM_EXISTS, new RoomMapper(), new JdbcTemplate(dataSource));
    }

    @Override
    protected Object[] getDeleteArgs(Room room) {
        return new Object[]{room.getId()};
    }

    @Override
    protected Object[] getUpdateArgs(Room room) {
        return new Object[]{room.getId()};
    }

    @Override
    protected Object[] getCreateArgs(Room room) {
        return new Object[]{room.getId()};
    }

    @Override
    protected Integer getExistArgs(Room room) {
        return room.getId();
    }
}
