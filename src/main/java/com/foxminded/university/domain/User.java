package com.foxminded.university.domain;

import java.util.Objects;

public class User {
    protected final String email;
    protected final String password;
    protected final String firstName;
    protected final String lastName;
    protected final Role role;

    protected User(UserBuilder<? extends UserBuilder> userUserBuilder) {

        this.email = userUserBuilder.email;
        this.password = userUserBuilder.password;
        this.firstName = userUserBuilder.firstName;
        this.lastName = userUserBuilder.lastName;
        this.role = userUserBuilder.role;
    }

    public static UserBuilder<? extends UserBuilder> builder() {
        return new UserBuilder();
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
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "User{" +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }

    public static class UserBuilder<SELF extends UserBuilder<SELF>> {

        public String email;
        public String password;
        public String firstName;
        public String lastName;
        public Role role;

        public UserBuilder() {
        }

        @SuppressWarnings("unchecked")
        public SELF self() {
            return (SELF) this;
        }

        public User build() {
            return new User(self());
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
