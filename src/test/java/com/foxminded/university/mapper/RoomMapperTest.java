package com.foxminded.university.mapper;

import com.foxminded.university.domain.Room;
import com.foxminded.university.mapper.RoomMapper;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoomMapperTest {

    private final ResultSet resultSet = mock(ResultSet.class);
    private final RowMapper<Room> roomMapper = new RoomMapper();

    @Test
    public void roomMapperShouldReturnRoom() throws SQLException {
        Room expected = new Room(111);
        when(resultSet.getRow()).thenReturn(1);
        when(resultSet.getInt("room_id")).thenReturn(111);
        Room room = roomMapper.mapRow(resultSet, 1);
        assertEquals(expected, room);
    }
}
