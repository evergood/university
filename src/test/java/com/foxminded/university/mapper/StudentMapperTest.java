package com.foxminded.university.mapper;

import com.foxminded.university.domain.Student;
import com.foxminded.university.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentMapperTest {

    private final ResultSet resultSet = mock(ResultSet.class);
    private final RowMapper<Student> studentMapper = new StudentMapper();

    @Test
    public void studentMapperShouldReturnStudent() throws SQLException {
        Student expected = Student.builder()
                .withId(11)
                .withFirstName("Garry")
                .withLastName("Cooper")
                .build();
        when(resultSet.getRow()).thenReturn(1);
        when(resultSet.getInt("student_id")).thenReturn(11);
        when(resultSet.getString("first_name")).thenReturn("Garry");
        when(resultSet.getString("last_name")).thenReturn("Cooper");
        Student student = studentMapper.mapRow(resultSet, 1);
        assertEquals(expected, student);
    }
}
