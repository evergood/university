package com.foxminded.university.domain;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
public class UserForm {

    @NotNull(message = "Can't be empty")
    @Size(min = 2, max = 30, message = "Must be more than 2 and less than 30 symbols")
    private String firstName;

    @NotNull(message = "Can't be empty")
    @Size(min = 2, max = 30, message = "Must be more than 2 and less than 30 symbols")
    private String lastName;

    @Email(message = "Enter valid e-mail")
    private String email;

    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})",
            message = "Enter valid password")
    private String password;

    private String passwordRepeat;

    private boolean passwordsEqual;

    @AssertTrue(message = "Passwords should match")
    public boolean isPasswordsEqual() {
        return password == null && passwordRepeat == null || Objects.equals(password, passwordRepeat);
    }
}
