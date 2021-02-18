package com.dev.cinema.model.dto;

import com.dev.cinema.annotation.FieldsValueMatch;
import com.dev.cinema.annotation.LoginConstraint;
import java.util.List;
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
    @NotNull
    private List<String> roles;

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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
