package com.foxminded.university.mapper;

import com.foxminded.university.domain.Role;
import com.foxminded.university.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("userMapper")
public class UserMapper implements RowMapper<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMapper.class);

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        LOGGER.debug("Start mapping");
        return resultSet.getRow() == 0 ? null : User.builder()
                .withEmail(resultSet.getString("email"))
                .withPassword(resultSet.getString("password"))
                .withFirstName(resultSet.getString("first_name"))
                .withLastName(resultSet.getString("last_name"))
                .withRole(Role.valueOf(resultSet.getString("role")))
                .build();
    }
}
