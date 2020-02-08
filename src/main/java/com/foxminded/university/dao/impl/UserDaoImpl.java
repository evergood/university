package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.UserDao;

import com.foxminded.university.domain.User;
import com.foxminded.university.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String SQL_FIND_USER = "SELECT * FROM users WHERE login = ?";
    private static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE login = ?";
    private static final String SQL_UPDATE_USER =
            "UPDATE users SET user_id =?, password = ?, first_name = ?, last_name = ?, role =? " +
                    "WHERE email = ?";
    private static final String SQL_INSERT_USER =
            "INSERT INTO users(email, password, user_id, first_name, last_name, role) VALUES(?,?,?,?,?,?)";
    private static final String SQL_USER_EXISTS = "SELECT EXISTS(SELECT FROM users WHERE login = ?)";

    @Autowired
    protected UserDaoImpl(DataSource dataSource) {
        super(SQL_FIND_USER, SQL_DELETE_USER, SQL_UPDATE_USER, SQL_INSERT_USER, SQL_USER_EXISTS,
                new UserMapper(), new JdbcTemplate(dataSource));
    }

    @Override
    protected Object[] getUpdateArgs(User user) {
        return new Object[]{user.getId(), user.getPassword(), user.getFirstName(),
               user.getLastName(), user.getRole(), user.getEmail()};
    }

    @Override
    protected Object[] getCreateArgs(User user) {
        return new Object[]{user.getEmail(), user.getPassword(), user.getId(),
        user.getFirstName(), user.getLastName(), user.getRole()};
    }

    @Override
    public Optional<User> getByEmail(String email) {
        User user = jdbcTemplate.queryForObject(SQL_FIND_USER_BY_EMAIL, new Object[]{email}, mapper);
        return Optional.ofNullable(user);
    }
}
