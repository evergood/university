package com.foxminded.university.domain;

public class Student extends Person {

    protected Student(StudentBuilder personPersonBuilder) {
        super(personPersonBuilder);
    }

    public static final class StudentBuilder extends PersonBuilder<StudentBuilder> {
        private Integer id;
        private String firstName;
        private String lastName;

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

        public StudentBuilder self() {
            return this;
        }

        public Student build() {
            return new Student(self());
        }
    }
}
