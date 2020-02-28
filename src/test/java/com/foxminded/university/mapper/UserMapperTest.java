package com.foxminded.university.mapper;

import com.foxminded.university.domain.Role;
import com.foxminded.university.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @Mock
    private ResultSet resultSet;
    private final UserMapper userMapper = new UserMapper();

    @Test
    void userMapperShouldReturnUser() throws SQLException {

        User expected = User.builder()
                .withEmail("123@gmail.com")
                .withPassword("124sfDGG#$")
                .withFirstName("Garry")
                .withLastName("Cooper")
                .withRole(Role.STUDENT)
                .build();
        when(resultSet.getRow()).thenReturn(1);
        when(resultSet.getString("email")).thenReturn("123@gmail.com");
        when(resultSet.getString("password")).thenReturn("124sfDGG#$");
        when(resultSet.getString("first_name")).thenReturn("Garry");
        when(resultSet.getString("last_name")).thenReturn("Cooper");
        when(resultSet.getString("role")).thenReturn("STUDENT");
        User actual = userMapper.mapRow(resultSet, 1);
        assertThat(expected, is(actual));
    }
}
