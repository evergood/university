package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;
import com.foxminded.university.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Repository("studentDao")
public class StudentDaoImpl extends AbstractDao<Student> implements StudentDao {

    private static final String SQL_FIND_STUDENT = "SELECT * FROM users WHERE user_id = ?";
    private static final String SQL_DELETE_STUDENT = "DELETE FROM users WHERE user_id = ?";
    private static final String SQL_UPDATE_STUDENT =
            "UPDATE users SET password = ?, first_name = ?, last_name = ? " +
                    "WHERE email = ?";
    private static final String SQL_INSERT_STUDENT =
            "INSERT INTO users(email, password, first_name, last_name, role) VALUES(?,?,?,?,?)";
    private static final String SQL_STUDENT_EXISTS = "SELECT EXISTS (SELECT FROM users WHERE user_id = ?)";
    private static final String SQL_VIEW_MARKS =
            "SELECT course_name, mark\n" +
                    "FROM (studentmarks JOIN courses ON studentmarks.course_id = courses.course_id)\n" +
                    "WHERE student_id = ?;";
    private static final String SQL_INSERT_STUDENT_TIMEUNIT = "INSERT INTO studenttimeunits" +
            "(student_id, course_name, timeunit_id) VALUES(?,?,?)";
    private static final String SQL_GET_STUDENT_SCHEDULE = "SELECT (course_name, timeunit_id)" +
            "FROM studenttimeunits WHERE student_id = ?";
    private static final String SQL_GET_ALL_STUDENTS = "SELECT * FROM users WHERE role = 'STUDENT' " +
            "ORDER BY user_id LIMIT ? OFFSET ?";
    private static final String SQL_NUM_OF_STUDENTS = "SELECT COUNT (*) FROM users WHERE role = 'STUDENT'";
    private final static String SQL_EXIST_BY_EMAIL = "SELECT EXISTS(SELECT FROM users WHERE email = ?)";
    private final static String SQL_GET_BY_EMAIL = "SELECT * FROM users WHERE email = ?";

    @Autowired
    protected StudentDaoImpl(StudentMapper studentMapper, JdbcTemplate jdbcTemplate) {
        super(SQL_FIND_STUDENT, SQL_DELETE_STUDENT, SQL_UPDATE_STUDENT,
                SQL_INSERT_STUDENT, SQL_STUDENT_EXISTS, SQL_NUM_OF_STUDENTS,
                SQL_GET_ALL_STUDENTS, studentMapper, jdbcTemplate);
    }

    @Override
    public Optional<Student> getById(Integer id) {
        Student student = jdbcTemplate.queryForObject(SQL_FIND_STUDENT, new Object[]{id}, mapper);
        return Optional.ofNullable(student);
    }

    @Override
    protected Object[] getUpdateArgs(Student student) {
        return new Object[]{student.getPassword(),
                student.getFirstName(), student.getLastName(), student.getEmail()};
    }

    @Override
    protected Object[] getCreateArgs(Student student) {
        return new Object[]{student.getEmail(), student.getPassword(),
                student.getFirstName(), student.getLastName(), student.getRole().name()};
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
    public boolean isExistByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_EXIST_BY_EMAIL, Boolean.class, email);
    }

    @Override
    public Optional<Student> getByEmail(String email) {
        if (isExistByEmail(email)) {
            Student student = jdbcTemplate.queryForObject(SQL_GET_BY_EMAIL, new Object[]{email}, mapper);
            return Optional.ofNullable(student);
        } else {
            return Optional.empty();
        }
    }
}
