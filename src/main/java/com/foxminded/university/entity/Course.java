package com.foxminded.university.entity;

import java.util.Objects;

public class Course {
    private Integer courseId;
    private String courseName;

    private Course(Builder builder) {
        courseId = builder.courseId;
        courseName = builder.courseName;
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
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId) &&
                Objects.equals(courseName, course.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName);
    }

    public static final class Builder {
        private Integer courseId;
        private String courseName;

        private Builder() {
        }

        public Builder withCourseId(Integer courseId) {
            this.courseId = courseId;
            return this;
        }

        public Builder withCourseName(String courseName) {
            this.courseName = courseName;
            return this;
        }

        public Course build() {
            return new Course(this);
        }
    }
}
