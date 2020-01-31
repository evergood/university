package com.foxminded.university.domain;

import java.util.List;

public class Student extends User {

    private List<Course> courses;

    protected Student(StudentBuilder personPersonBuilder) {
        super(personPersonBuilder);
    }

    public static Student.StudentBuilder builder() {
        return new StudentBuilder();
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "courses=" + courses +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public static final class StudentBuilder extends UserBuilder<StudentBuilder> {

        private List<Course> courses;

        private StudentBuilder() {
        }

        public StudentBuilder withCourses(List<Course> courses) {
            this.courses = courses;
            return self();
        }

        public StudentBuilder self() {
            return this;
        }

        public Student build() {
            return new Student(self());
        }
    }
}
