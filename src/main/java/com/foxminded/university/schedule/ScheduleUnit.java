package com.foxminded.university.schedule;

import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Room;
import com.foxminded.university.domain.TimeUnit;

public class ScheduleUnit {
    private Course course;
    private Room room;
    private TimeUnit timeUnit;

    private ScheduleUnit(Builder builder) {
        course = builder.course;
        room = builder.room;
        timeUnit = builder.timeUnit;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private Course course;
        private Room room;
        private TimeUnit timeUnit;

        private Builder() {
        }

        public Builder withCourse(Course course) {
            this.course = course;
            return this;
        }

        public Builder withRoom(Room room) {
            this.room = room;
            return this;
        }

        public Builder withTimeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
            return this;
        }

        public ScheduleUnit build() {
            return new ScheduleUnit(this);
        }
    }
}
