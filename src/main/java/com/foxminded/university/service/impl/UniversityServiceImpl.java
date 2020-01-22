package com.foxminded.university.service.impl;

import com.foxminded.university.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UniversityServiceImpl implements UniversityService {

    JdbcTemplate jdbcTemplate;

    @Autowired
    UniversityServiceImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final String SQL_LOGIN_EXISTS = "SELECT EXISTS (SELECT FROM loginpassword WHERE login = ?)";
    private static final String SQL_SIGN_UP = "INSERT INTO loginpassword (login, password) VALUES(?,?)";
    private static final String SQL_PUT_MARK =
            "INSERT INTO studentmarks (student_id, course_id, mark) VALUES(?,?,?)";
    private static final String SQL_VIEW_MARKS =
            "SELECT course_name, mark\n" +
                    "FROM (studentmarks JOIN courses ON studentmarks.course_id = courses.course_id)\n" +
                    "WHERE student_id = ?;";

    @Override
    public boolean checkLogin(String login) {
        return jdbcTemplate.queryForObject(SQL_LOGIN_EXISTS, Boolean.class, login);
    }

    @Override
    public boolean signUp(String login, String password) {
        return jdbcTemplate.update(SQL_SIGN_UP, login, password) > 0;
    }

    @Override
    public boolean putMark(Integer studentId, Integer courseId, Integer mark) {
        return jdbcTemplate.update(SQL_PUT_MARK, studentId, courseId, mark) > 0;
    }

    @Override
    public Map<String, Integer> viewMarks(Integer studentId) {
        return jdbcTemplate.query(SQL_VIEW_MARKS, (ResultSetExtractor<Map<String, Integer>>) rs -> {
            HashMap<String, Integer> mapResult = new HashMap<>();
            while (rs.next()) {
                mapResult.put(rs.getString("course_name"), rs.getInt("mark"));
            }
            return mapResult;
        });
    }
}
