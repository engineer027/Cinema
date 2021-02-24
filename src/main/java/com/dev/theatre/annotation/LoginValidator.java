package com.dev.theatre.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginValidator implements
        ConstraintValidator<LoginConstraint, String> {
    @Override
    public boolean isValid(String login,
                           ConstraintValidatorContext cxt) {
        return login != null && (login.length() > 8) && (login.length() < 14);
    }
}
