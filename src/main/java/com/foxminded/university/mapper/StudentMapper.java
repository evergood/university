package com.foxminded.university.mapper;

import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("studentMapper")
public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getRow() == 0 ? null : Student.builder()
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .build();
    }
}
