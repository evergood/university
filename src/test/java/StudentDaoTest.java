import com.foxminded.university.dao.CrudDao;
import com.foxminded.university.domain.Student;
import config.ConfigTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@JdbcTest
@ContextConfiguration(classes = ConfigTest.class)
public class StudentDaoTest {

    @Autowired
    @Qualifier("studentDao")
    CrudDao<Student> studentDao;

    @Test
    void studentDaoShouldInsertStudent() {
        Student expected = Student.builder()
                .withId(11)
                .withFirstName("Garry")
                .withLastName("Cooper")
                .build();
        studentDao.create(expected);
        Student student = studentDao.getById(11).get();
        assertEquals(expected, student);
    }

    @Test
    void studentDaoShouldReturnStudentById() {
        Student student = studentDao.getById(10).get();
        Student expected = Student.builder()
                .withFirstName("Emma")
                .withLastName("Lee")
                .withId(10)
                .build();
        assertEquals(expected, student);
    }

    @Test
    void studentDaoShouldUpdateStudent() {
        Student expected = Student.builder()
                .withFirstName("Garry")
                .withLastName("Cooper")
                .withId(5)
                .build();
        studentDao.update(expected);
        Student student = studentDao.getById(5).get();
        assertEquals(expected, student);
    }

    @Test
    void studentDaoShouldDeleteStudent() {
        Student student = Student.builder().withId(2).build();
        studentDao.delete(student);
        boolean isExist = studentDao.isExist(student);
        assertFalse(isExist);
    }
}
