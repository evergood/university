package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;
import com.foxminded.university.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository("studentDao")
public class StudentDaoImpl extends AbstractDao<Student> implements StudentDao {

    private static final String SQL_FIND_STUDENT = "SELECT * FROM users WHERE user_id = ?";
    private static final String SQL_DELETE_STUDENT = "DELETE FROM users WHERE user_id = ?";
    private static final String SQL_UPDATE_STUDENT =
            "UPDATE users SET email =?, password = ?, first_name = ?, last_name = ? " +
                    "WHERE user_id = ?";
    private static final String SQL_INSERT_STUDENT =
            "INSERT INTO users(email, password, user_id, first_name, last_name, role) VALUES(?,?,?,?,?,?)";
    private static final String SQL_STUDENT_EXISTS = "SELECT EXISTS (SELECT FROM users WHERE user_id = ?)";
    private static final String SQL_VIEW_MARKS =
            "SELECT course_name, mark\n" +
                    "FROM (studentmarks JOIN courses ON studentmarks.course_id = courses.course_id)\n" +
                    "WHERE student_id = ?;";
    private static final String SQL_INSERT_STUDENT_TIMEUNIT = "INSERT INTO studenttimeunits" +
            "(student_id, course_name, timeunit_id) VALUES(?,?,?)";
    private static final String SQL_GET_STUDENT_SCHEDULE = "SELECT (course_name, timeunit_id)" +
            "FROM studenttimeunits WHERE student_id = ?";
    private static final String SQL_GET_ALL_STUDENTS = "SELECT * FROM users WHERE role = STUDENT " +
            "ORDER BY user_id LIMIT ? OFFSET ?";
    private static final Integer LIMIT = 10;

    @Autowired
    protected StudentDaoImpl(DataSource dataSource) {
        super(SQL_FIND_STUDENT, SQL_DELETE_STUDENT, SQL_UPDATE_STUDENT,
                SQL_INSERT_STUDENT, SQL_STUDENT_EXISTS, new StudentMapper(), new JdbcTemplate(dataSource));
    }

    @Override
    public Optional<Student> getById(Integer id) {
        Student student = jdbcTemplate.queryForObject(SQL_FIND_STUDENT, new Object[]{id}, mapper);
        return Optional.ofNullable(student);
    }

    @Override
    protected Object[] getUpdateArgs(Student student) {
        return new Object[]{student.getEmail(), student.getPassword(),
                student.getFirstName(), student.getLastName(), student.getId()};
    }

    @Override
    protected Object[] getCreateArgs(Student student) {
        return new Object[]{student.getEmail(), student.getPassword(),
                student.getId(), student.getFirstName(), student.getLastName(), student.getRole().name()};
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

    @Override
    public boolean insertStudentTimeUnit(Integer studentId, String courseName, Integer timeInitId) {
        return jdbcTemplate.update(SQL_INSERT_STUDENT_TIMEUNIT, studentId, courseName, timeInitId) > 0;
    }

    @Override
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

    @Override
    public List<Student> getAllStudents(Integer pageNum) {
        int offset = (pageNum - 1) * LIMIT;
        return jdbcTemplate.query(SQL_GET_ALL_STUDENTS, new Object[]{LIMIT, offset},
                rs -> {
                    List<Student> listResult = new ArrayList<>();
                    while (rs.next()) {
                        listResult.add(mapper.mapRow(rs, 1));
                    }
                    return listResult;
                });
    }
}
