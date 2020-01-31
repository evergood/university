package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.LecturerDao;
import com.foxminded.university.domain.Lecturer;
import com.foxminded.university.mapper.LecturerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository("lecturerDao")
public class LecturerDaoImpl extends AbstractDao<Lecturer> implements LecturerDao {

    private static final String SQL_FIND_LECTURER = "SELECT * FROM users WHERE user_id = ?";
    private static final String SQL_DELETE_LECTURER = "DELETE FROM users WHERE user_id = ?";
    private static final String SQL_UPDATE_LECTURER =
            "UPDATE users SET email =?, password = ?, first_name = ?, last_name = ?, rank =? " +
                    "WHERE user_id = ?";
    private static final String SQL_INSERT_LECTURER =
            "INSERT INTO users(email, password, user_id, first_name, last_name, rank, role) VALUES(?,?,?,?,?,?,?)";
    private static final String SQL_LECTURER_EXISTS = "SELECT EXISTS(SELECT FROM users WHERE user_id = ?)";
    private static final String SQL_PUT_MARK = "INSERT INTO studentmarks (student_id, course_id, mark) VALUES(?,?,?)";


    @Autowired
    public LecturerDaoImpl(DataSource dataSource) {
        super(SQL_FIND_LECTURER, SQL_DELETE_LECTURER, SQL_UPDATE_LECTURER,
                SQL_INSERT_LECTURER, SQL_LECTURER_EXISTS, new LecturerMapper(), new JdbcTemplate(dataSource));
    }

    @Override
    protected Object[] getUpdateArgs(Lecturer lecturer) {
        return new Object[]{lecturer.getEmail(), lecturer.getPassword(),
                lecturer.getFirstName(), lecturer.getLastName(), lecturer.getRank(), lecturer.getId()};
    }

    @Override
    protected Object[] getCreateArgs(Lecturer lecturer) {
        return new Object[]{lecturer.getEmail(), lecturer.getPassword(),lecturer.getId(),
                lecturer.getFirstName(), lecturer.getLastName(), lecturer.getRank(), lecturer.getRole().toString()};
    }

    @Override
    public boolean putMark(Integer userId, Integer courseId, Character mark) {
        return jdbcTemplate.update(SQL_PUT_MARK, userId, courseId, mark) > 0;
    }
}
