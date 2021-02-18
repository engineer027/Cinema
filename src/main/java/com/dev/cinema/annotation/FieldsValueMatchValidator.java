package com.dev.cinema.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsValueMatchValidator
        implements ConstraintValidator<FieldsValueMatch, Object> {

    private String field;
    private String fieldMatch;

    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    public boolean isValid(Object user,
                           ConstraintValidatorContext context) {

        Object fieldValue = new BeanWrapperImpl(user)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(user)
                .getPropertyValue(fieldMatch);

        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }
    }
}
