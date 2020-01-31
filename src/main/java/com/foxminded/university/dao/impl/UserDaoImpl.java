package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.AbstractDao;
import com.foxminded.university.dao.UserDao;
import com.foxminded.university.domain.User;
import com.foxminded.university.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserDaoImpl extends AbstractDao<User, String> implements UserDao {

    private static final String SQL_FIND_USER = "SELECT * FROM users WHERE login = ?";
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE login = ?";
    private static final String SQL_UPDATE_USER = "UPDATE users SET password = ? WHERE login = ?";
    private static final String SQL_INSERT_USER = "INSERT INTO users(login, password) VALUES(?,?)";
    private static final String SQL_USER_EXISTS = "SELECT EXISTS(SELECT FROM users WHERE login = ?)";

    @Autowired
    protected UserDaoImpl(DataSource dataSource) {
        super(SQL_FIND_USER, SQL_DELETE_USER, SQL_UPDATE_USER, SQL_INSERT_USER, SQL_USER_EXISTS,
                new UserMapper(), new JdbcTemplate(dataSource));
    }

    @Override
    protected Object[] getDeleteArgs(User user) {
        return new Object[]{user.getLogin()};
    }

    @Override
    protected Object[] getUpdateArgs(User user) {
        return new Object[]{user.getPassword(), user.getLogin()};
    }

    @Override
    protected Object[] getCreateArgs(User user) {
        return new Object[]{user.getLogin(), user.getPassword()};
    }

    @Override
    protected String getExistArgs(User user) {
        return user.getLogin();
    }
}
