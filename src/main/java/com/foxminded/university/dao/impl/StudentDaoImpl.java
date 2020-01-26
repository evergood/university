package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.AbstractDao;
import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;
import com.foxminded.university.domain.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("studentDao")
public class StudentDaoImpl extends AbstractDao<Student, Integer> implements StudentDao {

    private static final String SQL_FIND_STUDENT = "SELECT * FROM students WHERE student_id = ?";
    private static final String SQL_DELETE_STUDENT = "DELETE FROM students WHERE student_id = ?";
    private static final String SQL_UPDATE_STUDENT = "UPDATE students SET first_name = ?, last_name = ? WHERE student_id = ?";
    private static final String SQL_INSERT_STUDENT = "INSERT INTO students(student_id, first_name, last_name) VALUES(?,?,?)";
    private static final String SQL_STUDENT_EXISTS = "SELECT EXISTS(SELECT FROM students WHERE student_id = ?)";
    private static final String SQL_PUT_MARK = "INSERT INTO studentmarks (student_id, course_id, mark) VALUES(?,?,?)";
    private static final String SQL_VIEW_MARKS =
            "SELECT course_name, mark\n" +
                    "FROM (studentmarks JOIN courses ON studentmarks.course_id = courses.course_id)\n" +
                    "WHERE student_id = ?;";
    private static final String SQL_VIEW_COURSE_STUDENTS = "SELECT course_name" +
            "FROM (studentcourses JOIN courses ON studentcourses.course_id = courses.course_id)" +
            "WHERE student_id = ?";
    private static final String SQL_INSERT_STUDENT_TIMEUNIT = "INSERT INTO studenttimeunits" +
            "(student_id, course_name, timeunit_id) VALUES(?,?,?)";
    private static final String SQL_GET_STUDENT_SCHEDULE = "SELECT (course_name, timeunit_id)" +
            "FROM studenttimeunits WHERE student_id = ?";

    @Autowired
    protected StudentDaoImpl(DataSource dataSource) {
        super(SQL_FIND_STUDENT, SQL_DELETE_STUDENT, SQL_UPDATE_STUDENT,
                SQL_INSERT_STUDENT, SQL_STUDENT_EXISTS, new StudentMapper(), new JdbcTemplate(dataSource));
    }

    @Override
    protected Object[] getDeleteArgs(Student student) {
        return new Object[]{student.getId()};
    }

    @Override
    protected Object[] getUpdateArgs(Student student) {
        return new Object[]{student.getFirstName(), student.getLastName(), student.getId()};
    }

    @Override
    protected Object[] getCreateArgs(Student student) {
        return new Object[]{student.getId(), student.getFirstName(), student.getLastName()};
    }

    @Override
    protected Integer getExistArgs(Student student) {
        return student.getId();
    }

    @Override
    public boolean putMark(Integer studentId, Integer courseId, Integer mark) {
        return jdbcTemplate.update(SQL_PUT_MARK, studentId, courseId, mark) > 0;
    }

    @Override
    public Map<String, Character> viewMarks(Integer studentId) {
        return jdbcTemplate.query(SQL_VIEW_MARKS, new Object[]{studentId},
                (ResultSetExtractor<Map<String, Character>>) rs -> {
                    HashMap<String, Character> mapResult = new HashMap<>();
                    while (rs.next()) {
                        mapResult.put(rs.getString("course_name"), rs.getString("mark").charAt(0));
                    }
                    return mapResult;
                });
    }

    public List<String> viewStudentCourses(Integer course_id) {
        return jdbcTemplate.query(SQL_VIEW_COURSE_STUDENTS, new Object[]{course_id}, rs -> {
            List<String> listResult = new ArrayList<>();
            while (rs.next()) {
                listResult.add(rs.getString("course_name"));
            }
            return listResult;
        });
    }

    public boolean insertStudentTimeUnit(Integer studentId, String courseName, Integer timeInitId) {
        return jdbcTemplate.update(SQL_INSERT_STUDENT_TIMEUNIT, studentId, courseName, timeInitId) > 0;
    }

    public Map<String, Integer> getStudentSchedule(Integer studentId) {
        return jdbcTemplate.query(SQL_GET_STUDENT_SCHEDULE, new Object[]{studentId},
                (ResultSetExtractor<Map<String, Integer>>) rs -> {
                    HashMap<String, Integer> mapResult = new HashMap<>();
                    while (rs.next()) {
                        mapResult.put(rs.getString("course_name"), rs.getInt("timeunit_id"));
                    }
                    return mapResult;
                });
    }
}
