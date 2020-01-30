package com.foxminded.university.mapper;

import com.foxminded.university.domain.Lecturer;
import com.foxminded.university.mapper.LecturerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LecturerMapperTest {

    private final ResultSet resultSet = mock(ResultSet.class);
    private final RowMapper<Lecturer> lecturerMapper = new LecturerMapper();

    @Test
    public void lecturerMapperShouldReturnLecturer() throws SQLException {
        Lecturer expected = Lecturer.builder()
                .withId(11)
                .withFirstName("Garry")
                .withLastName("Cooper")
                .build();
        when(resultSet.getRow()).thenReturn(1);
        when(resultSet.getInt("lecturer_id")).thenReturn(11);
        when(resultSet.getString("first_name")).thenReturn("Garry");
        when(resultSet.getString("last_name")).thenReturn("Cooper");
        Lecturer lecturer = lecturerMapper.mapRow(resultSet, 1);
        assertEquals(expected, lecturer);
    }
}
