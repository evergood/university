package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.CourseDao;
import com.foxminded.university.domain.Course;
import com.foxminded.university.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository("courseDao")
public class CourseDaoImpl extends AbstractDao<Course, Integer> implements CourseDao {

    private static final String SQL_FIND_COURSE = "SELECT * FROM courses WHERE course_id = ?";
    private static final String SQL_DELETE_COURSE = "DELETE FROM courses WHERE course_id = ?";
    private static final String SQL_UPDATE_COURSE = "UPDATE courses SET course_name = ? WHERE course_id = ?";
    private static final String SQL_INSERT_COURSE = "INSERT INTO courses(course_id, course_name) VALUES(?,?)";
    private static final String SQL_COURSE_EXISTS = "SELECT EXISTS(SELECT FROM courses WHERE course_id = ?)";

    @Autowired
    protected CourseDaoImpl(DataSource dataSource) {
        super(SQL_FIND_COURSE, SQL_DELETE_COURSE, SQL_UPDATE_COURSE,
                SQL_INSERT_COURSE, SQL_COURSE_EXISTS, new CourseMapper(), new JdbcTemplate(dataSource));
    }

    @Override
    protected Object[] getDeleteArgs(Course course) {
        return new Object[]{course.getId()};
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
    protected Integer getExistArgs(Course course) {
        return course.getId();
    }
}
