package com.dev.cinema.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginValidator implements
        ConstraintValidator<LoginConstraint, String> {

    @Override
    public void initialize(LoginConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String login,
                           ConstraintValidatorContext cxt) {
        return login != null && (login.length() > 8) && (login.length() < 14);
    }

}
