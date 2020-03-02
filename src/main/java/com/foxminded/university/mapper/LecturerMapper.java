package com.foxminded.university.mapper;

import com.foxminded.university.domain.Lecturer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("lecturerMapper")
public class LecturerMapper implements RowMapper<Lecturer> {

    @Override
    public Lecturer mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getRow() == 0 ? null :
                Lecturer.builder()
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .rank(resultSet.getString("rank"))
                        .build();
    }
}
