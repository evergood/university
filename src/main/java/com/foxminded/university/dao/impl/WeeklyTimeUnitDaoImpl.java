package com.foxminded.university.dao.impl;

import com.foxminded.university.dao.WeeklyTimeUnitDao;
import com.foxminded.university.domain.WeeklyTimeUnit;
import com.foxminded.university.mapper.WeeklyTimeUnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Time;

@Repository("weeklyTimeUnitDao")
public class WeeklyTimeUnitDaoImpl extends AbstractDao<WeeklyTimeUnit> implements WeeklyTimeUnitDao {

    private static final String SQL_FIND_WEEKLY_TIME_UNIT = "SELECT * FROM weeklytimeunits WHERE weekly_time_unit_id = ?";
    private static final String SQL_DELETE_WEEKLY_TIME_UNIT = "DELETE FROM weeklytimeunits WHERE weekly_time_unit_id = ?";
    private static final String SQL_UPDATE_WEEKLY_TIME_UNIT =
            "UPDATE weeklytimeunits SET weekday = ?, starttime = ?, endtime = ?  WHERE weekly_time_unit_id = ?";
    private static final String SQL_INSERT_WEEKLY_TIME_UNIT =
            "INSERT INTO weeklytimeunits(weekly_time_unit_id, weekday, starttime, endtime) VALUES(?,?,?,?)";
    private static final String SQL_WEEKLY_TIME_UNIT_EXISTS =
            "SELECT EXISTS(SELECT FROM weeklytimeunits WHERE weekly_time_unit_id = ?)";

    @Autowired
    protected WeeklyTimeUnitDaoImpl(DataSource dataSource) {
        super(SQL_FIND_WEEKLY_TIME_UNIT, SQL_DELETE_WEEKLY_TIME_UNIT, SQL_UPDATE_WEEKLY_TIME_UNIT,
                SQL_INSERT_WEEKLY_TIME_UNIT, SQL_WEEKLY_TIME_UNIT_EXISTS, new WeeklyTimeUnitMapper(),
                new JdbcTemplate(dataSource));
    }

    @Override
    protected Object[] getUpdateArgs(WeeklyTimeUnit weeklyTimeUnit) {
        return new Object[]{weeklyTimeUnit.getDayOfWeek().toString(),
                Time.valueOf(weeklyTimeUnit.getStartTime()), Time.valueOf(weeklyTimeUnit.getEndTime()),
                weeklyTimeUnit.getId()};
    }

    @Override
    protected Object[] getCreateArgs(WeeklyTimeUnit weeklyTimeUnit) {
        return new Object[]{weeklyTimeUnit.getId(),
                weeklyTimeUnit.getDayOfWeek().toString(), Time.valueOf(weeklyTimeUnit.getStartTime()),
                Time.valueOf(weeklyTimeUnit.getEndTime())};
    }

}
