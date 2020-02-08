package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.CourseDao;
import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.User;
import com.foxminded.university.mapper.CourseMapper;
import com.foxminded.university.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository("courseDao")
public class CourseDaoImpl extends AbstractDao<Course> implements CourseDao {

    private final StudentMapper studentMapper;

    private static final String SQL_FIND_COURSE = "SELECT * FROM courses WHERE course_id = ?";
    private static final String SQL_DELETE_COURSE = "DELETE FROM courses WHERE course_id = ?";
    private static final String SQL_UPDATE_COURSE = "UPDATE courses SET course_name = ? WHERE course_id = ?";
    private static final String SQL_INSERT_COURSE = "INSERT INTO courses(course_id, course_name) VALUES(?,?)";
    private static final String SQL_COURSE_EXISTS = "SELECT EXISTS(SELECT FROM courses WHERE course_id = ?)";
    private static final String SQL_GET_STUDENT_COURSES =
            "SELECT course_id, course_name\n" +
                    "FROM (studentcourses JOIN courses ON studentcourses.course_id = courses.course_id)\n" +
                    "WHERE user_id = ? \n" +
                    "ORDER BY users.user_id";
    private static final String SQL_FIND_STUDENTS_BY_COURSE_ID =
            "SELECT users.user_id, users.first_name, users.last_name" +
                    "FROM (studentcourses JOIN users ON studentcourses.user_id = users.user_id)" +
                    "WHERE course_id = ?";

    @Autowired
    protected CourseDaoImpl(DataSource dataSource, StudentMapper studentMapper) {
        super(SQL_FIND_COURSE, SQL_DELETE_COURSE, SQL_UPDATE_COURSE,
                SQL_INSERT_COURSE, SQL_COURSE_EXISTS, new CourseMapper(), new JdbcTemplate(dataSource));
        this.studentMapper = studentMapper;
    }

    @Override
    protected Object[] getUpdateArgs(Course course) {
        return new Object[]{course.getName(), course.getId()};
    }

    @Override
    protected Object[] getCreateArgs(Course course) {
        return new Object[]{course.getId(), course.getName()};
    }

    @Override
    public List<Course> findCoursesByStudentId(Integer studentId) {
        CourseMapper courseMapper = new CourseMapper();
        return jdbcTemplate.query(SQL_GET_STUDENT_COURSES, new Object[]{studentId}, rs -> {
            List<Course> courses = new ArrayList<>();
            while (rs.next()) {
                courses.add(courseMapper.mapRow(rs, 1));
            }
            return courses;
        });
    }

    @Override
    public List<User> findStudentsByCourseId(Integer courseId) {
        return jdbcTemplate.query(SQL_FIND_STUDENTS_BY_COURSE_ID, new Object[]{courseId}, rs -> {
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(studentMapper.mapRow(rs, 1));
            }
            return users;
        });
    }
}
