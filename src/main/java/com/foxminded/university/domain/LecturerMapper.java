package com.foxminded.university.domain;

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
                        .withId(resultSet.getInt("lecturer_id"))
                        .withFirstName(resultSet.getString("first_name"))
                        .withLastName(resultSet.getString("last_name"))
                        .build();
    }
}