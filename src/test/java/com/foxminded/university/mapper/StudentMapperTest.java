package com.foxminded.university.mapper;

import com.foxminded.university.domain.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentMapperTest {

    @Mock
    private ResultSet resultSet;
    private final StudentMapper studentMapper = new StudentMapper();

    @Test
    public void studentMapperShouldReturnStudent() throws SQLException {
        Student expected = Student.builder()
                .withFirstName("Garry")
                .withLastName("Cooper")
                .build();
        when(resultSet.getRow()).thenReturn(1);
        when(resultSet.getString("first_name")).thenReturn("Garry");
        when(resultSet.getString("last_name")).thenReturn("Cooper");
        Student actual = studentMapper.mapRow(resultSet, 1);
        assertThat(expected, is(actual));
    }
}
