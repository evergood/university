package com.foxminded.university.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

public class WeeklyTimeUnit {
    private final Integer id;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final DayOfWeek dayOfWeek;

    public Integer getId() {
        return id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    private WeeklyTimeUnit(Builder builder) {
        this.id = builder.id;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.dayOfWeek = builder.dayOfWeek;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WeeklyTimeUnit that = (WeeklyTimeUnit) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                dayOfWeek == that.dayOfWeek;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endTime, dayOfWeek);
    }

    @Override
    public String toString() {
        return " " + startTime +
                " - " + endTime +
                " on " + dayOfWeek;
    }

    public static final class Builder {
        private Integer id;
        private LocalTime startTime;
        private LocalTime endTime;
        private DayOfWeek dayOfWeek;

        private Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withStartTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder withEndTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder withDayOfWeek(DayOfWeek dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
            return this;
        }

        public WeeklyTimeUnit build() {
            return new WeeklyTimeUnit(this);
        }
    }
}
