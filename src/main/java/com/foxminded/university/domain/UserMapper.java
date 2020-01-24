package com.foxminded.university.domain;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("userMapper")
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getRow() == 0 ? null : User.builder()
                .withLogin(resultSet.getString("login"))
                .withPassword(resultSet.getString("password"))
                .build();
    }
}
