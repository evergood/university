package com.foxminded.university.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@ToString(callSuper = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Getter
@SuperBuilder
public class User {
    protected final String email;
    protected final String password;
    protected final String firstName;
    protected final String lastName;
    protected final Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName, role);
    }
}
