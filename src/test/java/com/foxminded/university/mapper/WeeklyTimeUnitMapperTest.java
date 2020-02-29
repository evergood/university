package com.foxminded.university.mapper;

import com.foxminded.university.domain.WeeklyTimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeeklyTimeUnitMapperTest {

    @Mock
    private ResultSet resultSet;
    private final RowMapper<WeeklyTimeUnit> weeklyTimeUnitMapper = new WeeklyTimeUnitMapper();

    @Test
    public void weeklyTimeUnitMapperShouldReturnWeeklyTimeUnit() throws SQLException {
        WeeklyTimeUnit expected = WeeklyTimeUnit.builder()
                .id(20)
                .startTime(LocalTime.of(10, 15))
                .endTime(LocalTime.of(12, 0))
                .dayOfWeek(DayOfWeek.MONDAY)
                .build();
        when(resultSet.getRow()).thenReturn(1);
        when(resultSet.getInt("weekly_time_unit_id")).thenReturn(20);
        when(resultSet.getTime("starttime")).thenReturn(Time.valueOf("10:15:00"));
        when(resultSet.getTime("endtime")).thenReturn(Time.valueOf("12:00:00"));
        when(resultSet.getString("weekday")).thenReturn("MONDAY");
        WeeklyTimeUnit actual = weeklyTimeUnitMapper.mapRow(resultSet, 1);
        assertThat(expected, is(actual));
    }
}
