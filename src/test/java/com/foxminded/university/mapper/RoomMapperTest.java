package com.foxminded.university.mapper;

import com.foxminded.university.domain.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoomMapperTest {

    @Mock
    private ResultSet resultSet;
    private final RowMapper<Room> roomMapper = new RoomMapper();

    @Test
    public void roomMapperShouldReturnRoom() throws SQLException {
        Room expected = new Room(111);
        when(resultSet.getRow()).thenReturn(1);
        when(resultSet.getInt("room_id")).thenReturn(111);
        Room actual = roomMapper.mapRow(resultSet, 1);
        assertThat(expected, is(actual));
    }
}
