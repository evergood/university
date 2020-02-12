package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.UserDao;
import com.foxminded.university.domain.Lecturer;
import com.foxminded.university.domain.User;
import com.foxminded.university.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String SQL_FIND_USER = "SELECT * FROM users WHERE user_id = ?";
    private static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE user_id = ?";
    private static final String SQL_UPDATE_USER =
            "UPDATE users SET password = ?, first_name = ?, last_name = ?, role =? " +
                    "WHERE email = ?";
    private static final String SQL_INSERT_USER =
            "INSERT INTO users(email, password, first_name, last_name) VALUES(?,?,?,?)";
    private static final String SQL_USER_EXISTS = "SELECT EXISTS(SELECT FROM users WHERE user_id = ?)";
    private static final String SQL_GET_ALL_USERS = "SELECT * FROM users" +
            "ORDER BY user_id LIMIT ? OFFSET ?";
    private static final String SQL_NUM_OF_USERS = "SELECT COUNT (*) FROM users";
    private final static String SQL_EXIST_BY_EMAIL = "SELECT EXISTS(SELECT FROM users WHERE email = ?)";
    private final static String SQL_GET_BY_EMAIL = "SELECT * FROM users WHERE email = ?";

    @Autowired
    protected UserDaoImpl(UserMapper userMapper, JdbcTemplate jdbcTemplate) {
        super(SQL_FIND_USER, SQL_DELETE_USER, SQL_UPDATE_USER, SQL_INSERT_USER, SQL_USER_EXISTS,
                SQL_NUM_OF_USERS, SQL_GET_ALL_USERS, userMapper, jdbcTemplate);
    }

    @Override
    protected Object[] getUpdateArgs(User user) {
        return new Object[]{user.getPassword(), user.getFirstName(),
                user.getLastName(), user.getRole().name(), user.getEmail()};
    }

    @Override
    protected Object[] getCreateArgs(User user) {
        return new Object[]{user.getEmail(), user.getPassword(),
                user.getFirstName(), user.getLastName()};
    }

    @Override
    public boolean isExistByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_EXIST_BY_EMAIL, Boolean.class, email);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        if (isExistByEmail(email)) {
            User user = jdbcTemplate.queryForObject(SQL_GET_BY_EMAIL, new Object[]{email}, mapper);
            return Optional.ofNullable(user);
        } else {
            return Optional.empty();
        }
    }
}
