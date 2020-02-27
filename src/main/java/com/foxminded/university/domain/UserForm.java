package com.foxminded.university.domain;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class UserForm {

    @NotNull(message = "Can't be empty")
    @Size(min = 2, max = 30, message = "Must be more than 2 and less than 30 symbols")
    private String firstName;

    @NotNull(message = "Can't be empty")
    @Size(min = 2, max = 30, message = "Must be more than 2 and less than 30 symbols")
    private String lastName;

    @Email(message = "Enter valid e-mail" )
    private String email;

    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})",
    message = "Enter valid password")
    private String password;

    private String passwordRepeat;

    private boolean passwordsEqual;

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

    public String getPasswordRepeat() {
        return passwordRepeat;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public void setPasswordsEqual(boolean passwordsEqual) {
        this.passwordsEqual = passwordsEqual;
    }

    @AssertTrue(message = "Passwords should match")
    public boolean isPasswordsEqual() {
        return password == null && passwordRepeat == null || Objects.equals(password, passwordRepeat);
    }
}
