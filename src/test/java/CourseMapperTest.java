import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.CourseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CourseMapperTest {

    private final ResultSet resultSet = mock(ResultSet.class);
    private final RowMapper<Course> courseMapper = new CourseMapper();

    @Test
    public void courseMapperShouldReturnCourse() throws SQLException {
        Course expected = new Course(11, "Rocket Science");
        when(resultSet.getRow()).thenReturn(1);
        when(resultSet.getInt("course_id")).thenReturn(11);
        when(resultSet.getString("course_name")).thenReturn("Rocket Science");
        Course course = courseMapper.mapRow(resultSet, 1);
        assertEquals(expected, course);
    }
}
