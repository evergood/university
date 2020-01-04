package com.foxminded.university.entity;

import java.time.MonthDay;
import java.util.Objects;

public class TimeUnit {
    private WeeklyTimeUnit weeklyTimeUnit;
    private MonthDay monthDay;

    private TimeUnit(Builder builder) {
        weeklyTimeUnit = builder.weeklyTimeUnit;
        monthDay = builder.monthDay;
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

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private WeeklyTimeUnit weeklyTimeUnit;
        private MonthDay monthDay;

        private Builder() {
        }

        public Builder withWeeklyTimeUnit(WeeklyTimeUnit weeklyTimeUnit) {
            this.weeklyTimeUnit = weeklyTimeUnit;
            return this;
        }

        public Builder withMonthDay(MonthDay monthDay) {
            this.monthDay = monthDay;
            return this;
        }

        public TimeUnit build() {
            return new TimeUnit(this);
        }
    }

}
