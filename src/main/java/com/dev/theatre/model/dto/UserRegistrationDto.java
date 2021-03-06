package com.dev.theatre.model.dto;

import com.dev.theatre.annotation.FieldsValueMatch;
import com.dev.theatre.annotation.LoginConstraint;
import javax.validation.constraints.NotNull;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "verifyPassword",
        message = "Passwords do not match!")
public class UserRegistrationDto {
    @NotNull
    private String password;
    @NotNull
    private String verifyPassword;
    @LoginConstraint(message = "Login is not correct")
    private String login;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
