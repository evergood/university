package com.foxminded.university.mapper;

import com.foxminded.university.domain.Course;
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
public class CourseMapperTest {

    @Mock
    private ResultSet resultSet;
    private final RowMapper<Course> courseMapper = new CourseMapper();

    @Test
    public void courseMapperShouldReturnCourse() throws SQLException {
        Course expected = new Course(11, "Rocket Science");
        when(resultSet.getRow()).thenReturn(1);
        when(resultSet.getInt("course_id")).thenReturn(11);
        when(resultSet.getString("course_name")).thenReturn("Rocket Science");
        Course actual = courseMapper.mapRow(resultSet, 1);
        assertThat(expected, is(actual));
    }
}
