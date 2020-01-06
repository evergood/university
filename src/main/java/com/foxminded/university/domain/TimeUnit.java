package com.foxminded.university.domain;

import java.time.MonthDay;
import java.util.Objects;

public class TimeUnit {
    private final WeeklyTimeUnit weeklyTimeUnit;
    private final MonthDay monthDay;

    public TimeUnit(WeeklyTimeUnit weeklyTimeUnit, MonthDay monthDay) {
        this.weeklyTimeUnit = weeklyTimeUnit;
        this.monthDay = monthDay;
    }

    public WeeklyTimeUnit getWeeklyTimeUnit() {
        return weeklyTimeUnit;
    }

    public MonthDay getMonthDay() {
        return monthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeUnit timeUnit = (TimeUnit) o;
        return Objects.equals(weeklyTimeUnit, timeUnit.weeklyTimeUnit) &&
                Objects.equals(monthDay, timeUnit.monthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weeklyTimeUnit, monthDay);
    }
}
