package com.foxminded.university.service.impl;

import com.foxminded.university.domain.User;
import com.foxminded.university.service.Validator;

import java.util.function.Function;
import java.util.regex.Pattern;

public class ValidatorImpl implements Validator<User> {

    private static final Pattern LOGIN_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]{3,16}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})");

    @Override
    public void validate(User user) {
        if (user == null) {
            throw new RuntimeException("Null user");
        }
        validateLogin(user);
        validatePassword(user);
    }

    private static void validateLogin(User user) {
        validateString(LOGIN_PATTERN, user, User::getLogin, "Login do not match the pattern");
    }

    private static void validatePassword(User user) {
        validateString(PASSWORD_PATTERN, user, User::getPassword, "Password do not match the pattern");
    }

    private static void validateString(Pattern pattern, User user, Function<User, String> function,
                                       String exceptionMessage) {
        if (!pattern.matcher(function.apply(user)).matches()) {
            throw new RuntimeException(exceptionMessage);
        }
    }
}
