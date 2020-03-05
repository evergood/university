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

    @NotNull(message = "{not.null}")
    @Size(min = 2, max = 30, message = "{length.error}")
    private String firstName;

    @NotNull(message = "{not.null}")
    @Size(min = 2, max = 30, message = "{length.error}")
    private String lastName;

    @Email(message = "{email.error}")
    private String email;

    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})",
            message = "{password.error}")
    private String password;

    private String passwordRepeat;

    private boolean passwordsEqual;

    @AssertTrue(message = "{passwords.error}")
    public boolean isPasswordsEqual() {
        return password == null && passwordRepeat == null || Objects.equals(password, passwordRepeat);
    }
}
