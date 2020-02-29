package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.LecturerDao;
import com.foxminded.university.domain.Lecturer;
import com.foxminded.university.mapper.LecturerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("lecturerDao")
public class LecturerDaoImpl extends AbstractDao<Lecturer> implements LecturerDao {

    private static final String SQL_FIND_LECTURER = "SELECT * FROM users WHERE user_id = ?";
    private static final String SQL_DELETE_LECTURER = "DELETE FROM users WHERE user_id = ?";
    private static final String SQL_UPDATE_LECTURER =
            "UPDATE users SET password = ?, first_name = ?, last_name = ?, rank = ? " +
                    "WHERE email = ?";
    private static final String SQL_INSERT_LECTURER =
            "INSERT INTO users(email, password, first_name, last_name, rank) VALUES(?,?,?,?,?)";
    private static final String SQL_LECTURER_EXISTS = "SELECT EXISTS(SELECT FROM users WHERE user_id = ?)";
    private static final String SQL_PUT_MARK = "INSERT INTO studentmarks (student_id, course_id, mark) VALUES(?,?,?)";
    private static final String SQL_GET_ALL_LECTURERS = "SELECT * FROM users WHERE role = 'LECTURER' " +
            "ORDER BY user_id LIMIT ? OFFSET ?";
    private static final String SQL_NUM_OF_LECTURERS = "SELECT COUNT (*) FROM users WHERE role = 'LECTURER'";
    private final static String SQL_EXIST_BY_EMAIL = "SELECT EXISTS(SELECT FROM users WHERE email = ?)";
    private final static String SQL_GET_BY_EMAIL = "SELECT * FROM users WHERE email = ?";


    @Autowired
    public LecturerDaoImpl(LecturerMapper lecturerMapper, JdbcTemplate jdbcTemplate) {
        super(SQL_FIND_LECTURER, SQL_DELETE_LECTURER, SQL_UPDATE_LECTURER,
                SQL_INSERT_LECTURER, SQL_LECTURER_EXISTS, SQL_NUM_OF_LECTURERS, SQL_GET_ALL_LECTURERS,
                lecturerMapper, jdbcTemplate);
    }

    @Override
    protected Object[] getUpdateArgs(Lecturer lecturer) {
        return new Object[]{lecturer.getPassword(), lecturer.getFirstName(),
                lecturer.getLastName(), lecturer.getRank(), lecturer.getEmail()};
    }

    @Override
    protected Object[] getCreateArgs(Lecturer lecturer) {
        return new Object[]{lecturer.getEmail(), lecturer.getPassword(), lecturer.getFirstName(),
                lecturer.getLastName(), lecturer.getRank()};
    }

    @Override
    public boolean putMark(Integer userId, Integer courseId, Character mark) {
        return jdbcTemplate.update(SQL_PUT_MARK, userId, courseId, mark) > 0;
    }

    @Override
    public boolean isExistByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_EXIST_BY_EMAIL, Boolean.class, email);
    }

    @Override
    public Optional<Lecturer> getByEmail(String email) {
        if (isExistByEmail(email)) {
            Lecturer lecturer = jdbcTemplate.queryForObject(SQL_GET_BY_EMAIL, new Object[]{email}, mapper);
            return Optional.ofNullable(lecturer);
        } else {
            return Optional.empty();
        }
    }
}
