package com.foxminded.university.domain;

public class User {
    private final String login;
    private final String password;

    private User(Builder builder) {
        this.login = builder.login;
        this.password = builder.password;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public static final class Builder {
        private String login;
        private String password;

        private Builder() {
        }

        public Builder withLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
