package com.foxminded.university.mapper;

import com.foxminded.university.domain.WeeklyTimeUnit;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;

@Component("weeklyTimeUnitMapper")
public class WeeklyTimeUnitMapper implements RowMapper<WeeklyTimeUnit> {
    @Override
    public WeeklyTimeUnit mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getRow() == 0 ? null :
                WeeklyTimeUnit.builder()
                        .id(resultSet.getInt("weekly_time_unit_id"))
                        .dayOfWeek(DayOfWeek.valueOf(resultSet.getString("weekday")))
                        .startTime(resultSet.getTime("starttime").toLocalTime())
                        .endTime(resultSet.getTime("endtime").toLocalTime())
                        .build();
    }
}
