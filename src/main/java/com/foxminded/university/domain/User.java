package com.foxminded.university.domain;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class User {
    protected final String email;
    protected final String password;
    protected final String firstName;
    protected final String lastName;
    protected final Role role;
}
