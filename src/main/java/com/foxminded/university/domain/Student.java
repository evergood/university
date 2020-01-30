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

        public StudentBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public StudentBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public StudentBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
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
