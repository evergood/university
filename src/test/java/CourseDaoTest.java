import com.foxminded.university.dao.CrudDao;
import com.foxminded.university.domain.Course;
import config.ConfigTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@JdbcTest
@ContextConfiguration(classes = ConfigTest.class)
public class CourseDaoTest {

    @Autowired
    @Qualifier("courseDao")
    CrudDao<Course> courseDao;

    @Test
    void courseDaoShouldInsertCourse() {
        Course expected = new Course(11, "Rocket Science");
        courseDao.create(expected);
        Course course = courseDao.getById(11).get();
        assertEquals(expected, course);
    }

    @Test
    void courseDaoShouldReturnCourseById() {
        Course course = courseDao.getById(7).get();
        Course expected = new Course(7, "Biology");
        assertEquals(expected, course);
    }

    @Test
    void courseDaoShouldUpdateCourse() {
        Course expected = new Course(7, "Rocket Science");
        courseDao.update(expected);
        Course course = courseDao.getById(7).get();
        assertEquals(expected, course);
    }

    @Test
    void courseDaoShouldDeleteCourse() {
        Course course = new Course(7, "Rocket Science");
        courseDao.delete(course);
        boolean isExist = courseDao.isExist(course);
        assertFalse(isExist);
    }
}
