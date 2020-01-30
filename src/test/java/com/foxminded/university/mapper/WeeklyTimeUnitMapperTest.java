package com.foxminded.university.mapper;

import com.foxminded.university.domain.WeeklyTimeUnit;
import com.foxminded.university.mapper.WeeklyTimeUnitMapper;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeeklyTimeUnitMapperTest {

    private final ResultSet resultSet = mock(ResultSet.class);
    private final RowMapper<WeeklyTimeUnit> weeklyTimeUnitMapper = new WeeklyTimeUnitMapper();

    @Test
    public void weeklyTimeUnitMapperShouldReturnWeeklyTimeUnit() throws SQLException {
        WeeklyTimeUnit expected = WeeklyTimeUnit.builder()
                .withId(20)
                .withStartTime(LocalTime.of(10, 15))
                .withEndTime(LocalTime.of(12, 0))
                .withDayOfWeek(DayOfWeek.MONDAY)
                .build();
        when(resultSet.getRow()).thenReturn(1);
        when(resultSet.getInt("weekly_time_unit_id")).thenReturn(20);
        when(resultSet.getTime("starttime")).thenReturn(Time.valueOf("10:15:00"));
        when(resultSet.getTime("endtime")).thenReturn(Time.valueOf("12:00:00"));
        when(resultSet.getString("weekday")).thenReturn("MONDAY");
        WeeklyTimeUnit weeklyTimeUnit = weeklyTimeUnitMapper.mapRow(resultSet, 1);
        assertEquals(expected, weeklyTimeUnit);
    }
}
