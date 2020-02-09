package com.foxminded.university.service.major;

import com.foxminded.university.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ValidatorImplTest {

    private final ValidatorImpl validator = new ValidatorImpl();

    @Test
    void validatorShouldValidateUser() {
        String email = "123@gmail.com";
        String password = "14ffGFR#$%";
        User user = User.builder().withEmail(email).withPassword(password).build();
        assertDoesNotThrow(() -> validator.validate(user));
    }

    @Test
    void validatorShouldThrowWhenEmailIsNotValid() {
        String email = "123gmail.com";
        String password = "14ffGFR#$%";
        User user = User.builder().withEmail(email).withPassword(password).build();
        Exception exception = assertThrows(RuntimeException.class, () -> validator.validate(user));
        String expected = "E-mail does not match the pattern";
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void validatorShouldThrowWhenPasswordIsNotValid() {
        String email = "123@gmail.com";
        String password = "1111";
        User user = User.builder().withEmail(email).withPassword(password).build();
        Exception exception = assertThrows(RuntimeException.class, () -> validator.validate(user));
        String expected = "Password do not match the pattern";
        assertEquals(expected, exception.getMessage());
    }
}