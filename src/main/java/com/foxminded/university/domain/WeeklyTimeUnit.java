package com.foxminded.university.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class WeeklyTimeUnit {
    private LocalTime startTime;
    private LocalTime endTime;
    private DayOfWeek dayOfWeek;

    private WeeklyTimeUnit(Builder builder) {
        startTime = builder.startTime;
        endTime = builder.endTime;
        dayOfWeek = builder.dayOfWeek;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private LocalTime startTime;
        private LocalTime endTime;
        private DayOfWeek dayOfWeek;

        private Builder() {
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
