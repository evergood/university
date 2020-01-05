package com.foxminded.university.domain;

public class Lecturer extends Person {

    protected Lecturer(LecturerBuilder personPersonBuilder) {
        super(personPersonBuilder);
    }

    public static final class LecturerBuilder extends PersonBuilder<LecturerBuilder> {
        private Integer id;
        private String firstName;
        private String lastName;

        private LecturerBuilder() {
        }

        public LecturerBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public LecturerBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public LecturerBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public LecturerBuilder self() {
            return this;
        }

        public Lecturer build() {
            return new Lecturer(self());
        }
    }
}
