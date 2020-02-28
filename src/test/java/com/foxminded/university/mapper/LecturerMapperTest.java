package com.foxminded.university.mapper;

import com.foxminded.university.domain.Lecturer;
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
public class LecturerMapperTest {

    @Mock
    private ResultSet resultSet;
    private final RowMapper<Lecturer> lecturerMapper = new LecturerMapper();

    @Test
    public void lecturerMapperShouldReturnLecturer() throws SQLException {
        Lecturer expected = Lecturer.builder()
                .withEmail("123@gmail.com")
                .withPassword("123_456")
                .withFirstName("Garry")
                .withLastName("Cooper")
                .withRank("Professor")
                .build();
        when(resultSet.getRow()).thenReturn(1);
        when(resultSet.getString("email")).thenReturn("123@gmail.com");
        when(resultSet.getString("password")).thenReturn("123_456");
        when(resultSet.getString("first_name")).thenReturn("Garry");
        when(resultSet.getString("last_name")).thenReturn("Cooper");
        when(resultSet.getString("rank")).thenReturn("Professor");
        Lecturer actual = lecturerMapper.mapRow(resultSet, 1);
        assertThat(expected, is(actual));
    }
}
