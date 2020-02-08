package com.foxminded.university.domain;

public class Lecturer extends User {

    protected final String rank;

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "rank='" + rank + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }

    protected Lecturer(LecturerBuilder userUserBuilder) {
        super(userUserBuilder);
        this.rank = userUserBuilder.rank;
    }

    public static LecturerBuilder builder() {
        return new LecturerBuilder();
    }

    public static final class LecturerBuilder extends UserBuilder<LecturerBuilder> {

        protected String rank;

        public LecturerBuilder() {
        }

        public LecturerBuilder withRank(String rank) {
            this.rank = rank;
            return self();
        }

        public LecturerBuilder self() {
            return this;
        }

        public Lecturer build() {
            return new Lecturer(self());
        }
    }
}
