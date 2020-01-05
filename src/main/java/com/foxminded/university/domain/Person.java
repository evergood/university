package com.foxminded.university.domain;

import java.util.Objects;

public class Person {
    protected final Integer id;
    protected final String firstName;
    protected final String lastName;

    protected Person(PersonBuilder<? extends PersonBuilder> personPersonBuilder) {
        this.id = personPersonBuilder.id;
        this.firstName = personPersonBuilder.firstName;
        this.lastName = personPersonBuilder.lastName;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    public static class PersonBuilder<SELF extends PersonBuilder<SELF>> {
        private Integer id;
        private String firstName;
        private String lastName;

        protected PersonBuilder() {
        }

        @SuppressWarnings("unchecked")
        public SELF self() {
            return (SELF) this;
        }

        public Person build() {
            return new Person(self());
        }

        public SELF withId(Integer id) {
            this.id = id;
            return self();
        }

        public SELF withFirstName(String firstName) {
            this.firstName = firstName;
            return self();
        }

        public SELF withLastName(String lastName) {
            this.lastName = lastName;
            return self();
        }
    }
}
