package com.foxminded.university.service.major;

import com.foxminded.university.domain.User;
import com.foxminded.university.service.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.regex.Pattern;

@Component("validator")
@Slf4j
public class ValidatorImpl implements Validator<User> {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&*+/=?`{}~^.-]+@[a-zA-Z0-9.-]+$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})");

    @Override
    public void validate(User user) {
        LOGGER.debug("Starting validation");
        if (user == null) {
            throw new RuntimeException("Null user");
        }
        validateLogin(user);
        validatePassword(user);
        LOGGER.debug("Validation successful");
    }

    private static void validateLogin(User user) {
        validateString(EMAIL_PATTERN, user, User::getEmail, "E-mail does not match the pattern");
    }

    private static void validatePassword(User user) {
        validateString(PASSWORD_PATTERN, user, User::getPassword, "Password do not match the pattern");
    }

    private static void validateString(Pattern pattern, User user, Function<User, String> function,
                                       String exceptionMessage) {
        if (!pattern.matcher(function.apply(user)).matches()) {
            LOGGER.error(exceptionMessage);
            throw new RuntimeException(exceptionMessage);
        }
    }
}
