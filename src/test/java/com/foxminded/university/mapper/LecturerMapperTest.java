package com.foxminded.university.mapper;

import com.foxminded.university.domain.Lecturer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LecturerMapperTest {

    @Mock
    private ResultSet resultSet;
    private final RowMapper<Lecturer> lecturerMapper = new LecturerMapper();

    @Test
    public void lecturerMapperShouldReturnLecturer() throws SQLException {
        Lecturer expected = Lecturer.builder()
                .withId(11)
                .withFirstName("Garry")
                .withLastName("Cooper")
                .withRank("Professor")
                .build();
        when(resultSet.getRow()).thenReturn(1);
        when(resultSet.getInt("user_id")).thenReturn(11);
        when(resultSet.getString("first_name")).thenReturn("Garry");
        when(resultSet.getString("last_name")).thenReturn("Cooper");
        when(resultSet.getString("rank")).thenReturn("Professor");
        Lecturer lecturer = lecturerMapper.mapRow(resultSet, 1);
        assertEquals(expected, lecturer);
    }
}
