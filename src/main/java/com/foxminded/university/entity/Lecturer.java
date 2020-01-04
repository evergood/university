package com.foxminded.university.entity;

public class Lecturer extends Person {

    private Lecturer(Builder builder) {
        id = builder.id;
        firstName = builder.firstName;
        lastName = builder.lastName;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private String firstName;
        private String lastName;

        private Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Lecturer build() {
            return new Lecturer(this);
        }
    }
}
