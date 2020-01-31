package com.foxminded.university.domain;

import java.util.Objects;

public class User {
    protected final Integer id;
    protected final String email;
    protected final String password;
    protected final String firstName;
    protected final String lastName;
    protected final Role role;

    protected User(UserBuilder<? extends UserBuilder> userUserBuilder) {

        this.id = userUserBuilder.id;
        this.email = userUserBuilder.email;
        this.password = userUserBuilder.password;
        this.firstName = userUserBuilder.firstName;
        this.lastName = userUserBuilder.lastName;
        this.role = userUserBuilder.role;
    }

    public static UserBuilder<? extends UserBuilder> builder() {
        return new UserBuilder();
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    public static class UserBuilder<SELF extends UserBuilder<SELF>> {

        protected Integer id;
        protected String email;
        protected String password;
        protected String firstName;
        protected String lastName;
        protected Role role;

        protected UserBuilder() {
        }

        @SuppressWarnings("unchecked")
        public SELF self() {
            return (SELF) this;
        }

        public User build() {
            return new User(self());
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

        public SELF withEmail(String email) {
            this.email = email;
            return self();
        }

        public SELF withPassword(String password) {
            this.password = password;
            return self();
        }

        public SELF withRole(Role role) {
            this.role = role;
            return self();
        }
    }
}
